package com.an.employee.repository;

import com.an.employee.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

   Optional<Course> findByTitle(String title);
   @Transactional
   @Modifying
   void deleteByTitle(String title);
}
