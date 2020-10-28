package me.bolf.OneToManyRelation.repository;

import me.bolf.OneToManyRelation.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {}
