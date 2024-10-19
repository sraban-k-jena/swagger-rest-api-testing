package com.jt.student_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.jt.student_rest.model.Student;
import com.jt.student_rest.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
@RequestMapping("/student")
@Tag(name = "Student Controller", description = "manage Students")
public class StudentController {

    @Autowired
    public StudentService service;

    @GetMapping
    @Operation(summary = "Get All Products ", description = "All ID are Retrived .")
    @ApiResponse(responseCode = "200", description = "List of Student is retrived .")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> student = service.getAllStudent();
        return ResponseEntity.ok(student);
    }

    @GetMapping("/getStudent/{id}")
    @Operation(summary = "Get One Student", description = "Retrive one Student details .")
    @ApiResponse(responseCode = "200", description = "Id of Student is retrived .")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = service.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    @Operation(summary = "Created a new Student", description = "Craete a new Student details .")
    @ApiResponse(responseCode = "201", description = "Created new Student details.")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student student2 = service.addStudent(student);
        return new ResponseEntity<>(student2, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    @Operation(summary = "Update STudent ", description = "Update STudent Details.")
    @ApiResponse(responseCode = "200", description = "Edit the  of Student details .")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student student2 = service.updateStudent(id, student);
        return new ResponseEntity<>(student2, HttpStatus.OK);
    }

    @PatchMapping("/{id}/price/{price}")
    @Operation(summary = "Update Student Price", description = "Update STudent Price .")
    @ApiResponse(responseCode = "200", description = "Edit the Price of Student details  .")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Student> updatePrice(@PathVariable int id, @PathVariable double price) {
        Student student = service.updateStudentPrice(id, price);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete All Student", description = "Delete All Students.")
    @ApiResponse(responseCode = "204", description = "Delete all student .")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Void> deleteAll() {
        service.allStudentDelete();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete One Student Record.", description = "Delete All Student .")
    @ApiResponse(responseCode = "204", description = "Delete all Student.")
    @ApiResponse(responseCode = "404", description = "Server not found")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        service.studentDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/updatePassword/{password}")
    @Operation(summary = "Password Update", description = "New Password is generated .")
    @ApiResponse(responseCode = "200", description = "Update a new Password .")
    @ApiResponse(responseCode = "404", description = "Server not Found .")
    public ResponseEntity<Student> updatePasword(@PathVariable int id, @PathVariable String password) {
        Student student = service.updatePassword(id, password);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
