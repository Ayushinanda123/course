package com.an.employee.controller;

import com.an.employee.dto.CourseDto;
import com.an.employee.dto.ResponseDto;
import com.an.employee.service.ICourseService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
@Tag(
        name="Course Controller",
        description="Course Controller for CRUD operations for UKG Training",
        externalDocs= @ExternalDocumentation(
                url="ayushinanda@gmail.com",
                description="The external Documentation Description"
        )
)
@Validated
@RestController
@RequestMapping("/api")
public class CourseController {
    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private ICourseService iCourseService;

    @Operation(
            description = "Create new Employee operations",
            summary = "Post API to create new employee in the system"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Success in creating employee"

    )

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCourse(@RequestBody @Valid CourseDto courseDto){
        iCourseService.createCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created Successfully","201")
        );
    }
    @GetMapping("/fetchByTitle")
    public ResponseEntity<CourseDto>fetchCourseByTitle(@RequestParam String title){
        CourseDto courseDto=iCourseService.fetchCourseByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }
    @GetMapping("/fetch")
    public ResponseEntity<CourseDto> fetchCourse(Integer id) {
        CourseDto courseDto = iCourseService.fetchCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto>updateCourse(@RequestBody @Valid CourseDto courseDto){
        boolean isUpdated= iCourseService.updateCourse(courseDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Updated Successfully","203"));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Not updated","501"));
        }
    }
    @GetMapping("/fetch-all")
    public List<CourseDto> fetchAllCourse(){
//        List<CourseDto> courseList = (List<CourseDto>) iCourseService.fetchAllCourses();
//        return ResponseEntity.status(HttpStatus.OK).body(courseList).getBody();
        return iCourseService.fetchAllCourses();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCourse(@RequestParam String title){
        boolean isDeleted= iCourseService.deleteCourse(title);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Deleted Successfully","200"));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Not deleted","501"));
        }
    }
    @GetMapping("/greet")
    public String greet(){
        return "Hello world!";
    }
    @GetMapping("/build-info")
    public String buildInfo(){
        return buildVersion;
    }
}
