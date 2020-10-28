package me.bolf.OneToManyRelation.repository;

import me.bolf.OneToManyRelation.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {}
