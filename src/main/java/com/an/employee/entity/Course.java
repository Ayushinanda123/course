package com.an.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Course title can not be empty")
    @NotNull(message = "Course title name can not be empty")
    private String title;
    @NotEmpty(message = "Course duration can not be empty")
    @NotNull(message = "Course duration name can not be empty")
    private String duration;
    @NotEmpty(message = "Course author can not be empty")
    @NotNull(message = "Course author name can not be empty")
    private String author;
}
