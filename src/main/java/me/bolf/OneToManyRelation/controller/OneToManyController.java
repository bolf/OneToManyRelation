package me.bolf.OneToManyRelation.controller;

import me.bolf.OneToManyRelation.entity.Course;
import me.bolf.OneToManyRelation.entity.Instructor;
import me.bolf.OneToManyRelation.entity.Review;
import me.bolf.OneToManyRelation.repository.CourseRepository;
import me.bolf.OneToManyRelation.repository.InstructorRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@Controller
public class OneToManyController {
    private InstructorRepository instructorRepository;
    private CourseRepository courseRepository;

    public OneToManyController(InstructorRepository instructorRepository,CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Instructor> postHandler(@RequestBody LinkedHashMap requestBody) {
        Instructor instructor = new Instructor();
        instructor.setName(requestBody.get("name").toString());
        instructor.setLastname(requestBody.get("lastname").toString());
        instructor.add(new Course(requestBody.get("courseTitle").toString()));
        instructor.add(new Course(requestBody.get("courseTitle1").toString()));
        instructor.add(new Course(requestBody.get("courseTitle2").toString()));
        Course tmpCourse = instructor.getCourses().get(1);
        tmpCourse.addReview(new Review("review comment to the course"));
        tmpCourse.addReview(new Review("second review comment to the course"));
        instructorRepository.save(instructor);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Instructor> getHandler(@RequestParam Long id){
        Instructor instructor = instructorRepository.findById(id).get();
        return new ResponseEntity<>(instructor,HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Long> deleteHandler(@RequestParam Long id){
        instructorRepository.deleteById(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @DeleteMapping("/course")
    public ResponseEntity<Long> deleteCourseHandler(@RequestParam Long id){
        courseRepository.deleteById(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}

