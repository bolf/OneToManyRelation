package me.bolf.OneToManyRelation.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructors")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id") //prevents circular serialization
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor",
        cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Course> courses;

    public void add(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    public Instructor(String name, String lastname, List<Course> courses) {
        this.name = name;
        this.lastname = lastname;
        this.courses = courses;
    }

    public Instructor() {}

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
