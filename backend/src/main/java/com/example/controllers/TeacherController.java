package com.example.controllers;

import com.example.models.Course;
import com.example.models.OptionalProposal;
import com.example.models.Teacher;
import com.example.payload.responses.CourseDTO;
import com.example.services.ITeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping("/courses")
    @PreAuthorize("hasRole('TEACHER') or hasRole('CHIEF')")
    public List<CourseDTO> getTeacherCourses(@RequestHeader("UserId") Long userId) {
        Teacher teacher = this.teacherService.findTeacherById(userId).orElse(null);

        if (teacher == null) {
            return null;
        } else {
            // convert mandatory and optional Courses to DTOs
            List<CourseDTO> courseDTOS = this.teacherService.getAllCourses(teacher)
                    .stream()
                    .map(Course::convertToCourseDTO)
                    .collect(Collectors.toList());

            // convert pending or rejected OptionalProposals to DTOs
            List<CourseDTO> optionalDTOs = this.teacherService.getAllOptionalProposals(teacher)
                    .stream()
                    .map(OptionalProposal::convertToCourseDTO)
                    .collect(Collectors.toList());

            return Stream.concat(courseDTOS.stream(), optionalDTOs.stream()).collect(Collectors.toList());
        }
    }
}
