package com.jt.student_rest.service;

import java.util.List;

import com.jt.student_rest.model.Student;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudent();

    Student getStudent(int id);

    Student updateStudent(int id, Student student);

    Student updateStudentPrice(int id, double price);

    void allStudentDelete();

    void studentDelete(int id);

    Student updatePassword(int id, String password);
}
