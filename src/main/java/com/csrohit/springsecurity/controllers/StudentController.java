package com.csrohit.springsecurity.controllers;

import com.csrohit.springsecurity.models.Student;
import com.csrohit.springsecurity.models.StudentPage;
import com.csrohit.springsecurity.services.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Contact;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    @ApiOperation(
            value = "Returns page of student",
            notes = "list all the users in the database",
            nickname = "getStudents",
            response = Student.class)
    public ResponseEntity<Page<Student>> getStudents(
            @RequestParam int pageNumber) {
        return new ResponseEntity<>(studentService.getStudents(new StudentPage()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
    }
}
