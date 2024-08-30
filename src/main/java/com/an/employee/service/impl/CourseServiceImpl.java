package com.an.employee.service.impl;


import com.an.employee.dto.CourseDto;
import com.an.employee.entity.Course;
import com.an.employee.exception.CourseAlreadyExistsException;
import com.an.employee.exception.CourseNotFoundExcetion;
import com.an.employee.mapper.CourseMapper;
import com.an.employee.repository.CourseRepository;
import com.an.employee.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository repository;

    @Override
    public void createCourse(CourseDto courseDto) {
        Optional<Course> courseOptional =repository.findByTitle(courseDto.getTitle());
        if(courseOptional.isPresent()){
            throw new CourseAlreadyExistsException("Course already exists with title - "+ courseDto.getTitle());
        }
        Course course=CourseMapper.mapToCourse(courseDto,new Course());
        repository.save(course);
    }

    @Override
    public CourseDto fetchCourseByTitle(String title) {
        Course course=repository.findByTitle(title).orElseThrow(
                ()-> new CourseNotFoundExcetion("Course does not exists for title - "+ title)
        );
        CourseDto courseDto= CourseMapper.mapToCourseDto(course, new CourseDto());
        return courseDto;
    }

    @Override
    public boolean updateCourse(CourseDto courseDto) {
        boolean isUpdated=false;
        if(courseDto.getTitle()==null){
            return isUpdated;
        }
        Course course=repository.findByTitle(courseDto.getTitle()).orElseThrow(
                ()-> new CourseNotFoundExcetion("Course does not exists for title - "+ courseDto.getTitle())
        );
        Course updatedCourse =CourseMapper.mapToCourse(courseDto,course);
        repository.save(updatedCourse);
        isUpdated=true;
        return isUpdated;
    }



    @Override
    public boolean deleteCourse(String title) {
        boolean isDeleted=false;
        if(title == null){
            return isDeleted;
        }
        repository.findByTitle(title).orElseThrow(
                ()-> new CourseNotFoundExcetion("Course does not exists for title - "+ title)
        );
        repository.deleteByTitle(title);
        isDeleted=true;
        return isDeleted;
    }

    @Override
    public List<CourseDto> fetchAllCourses() {
        List<Course> courses = repository.findAll();
        if (courses.isEmpty()) {
            throw new CourseNotFoundExcetion("No courses found");
        }

        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @Override
    public CourseDto fetchCourse(Integer id) {
        Course course = repository.findById(id).orElseThrow(
                () -> new CourseNotFoundExcetion("Course does not exists for id - " + id)
        );

        CourseDto courseDto = CourseMapper.mapToCourseDto(course, new CourseDto());
        return courseDto;
    }
}
