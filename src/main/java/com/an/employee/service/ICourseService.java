package com.an.employee.service;

import com.an.employee.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    void createCourse(CourseDto courseDto);

    CourseDto fetchCourseByTitle(String title);

    boolean updateCourse(CourseDto courseDto);

    boolean deleteCourse(String title);

    List<CourseDto> fetchAllCourses();

    CourseDto fetchCourse(Integer id);
}
