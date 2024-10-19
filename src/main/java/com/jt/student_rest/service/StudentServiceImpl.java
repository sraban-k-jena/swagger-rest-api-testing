package com.jt.student_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jt.student_rest.model.Student;
import com.jt.student_rest.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {
        try {
            return studentRepo.save(student);
        } catch (Exception ee) {
            System.out.println("Error is :" + ee.getMessage());
            throw new RuntimeException("Failed to add Student ." + ee);
        }
    }

    @Override
    public List<Student> getAllStudent() {
        try {
            List<Student> students = studentRepo.findAll();
            return students;
        } catch (Exception ee) {
            System.out.println("Error is :" + ee.getMessage());
            throw new RuntimeException("Faild to retrive Student :" + ee);
        }
    }

    @Override
    public Student getStudent(int id) {
        try {
            if (!studentRepo.existsById(id)) {
                throw new IllegalArgumentException(id + " does not exists .");
            }
            return studentRepo.findById(id).orElseThrow(() -> new RuntimeException());
        } catch (Exception ee) {
            System.out.println("The Error is :" + ee);
            throw new RuntimeException("This is Faild to connect to get a student .");
        }

    }

    @Override
    public Student updateStudent(int id, Student student) {
        try {
            if (!studentRepo.existsById(id)) {
                throw new IllegalArgumentException(id + ":is not exist .");
            }
            student.setId(id);
            return studentRepo.save(student);
        } catch (Exception ee) {
            System.out.println("The Error is :" + ee);
            throw new RuntimeException("This is Faild to connect to get a student .");
        }
    }

    @Override
    public Student updateStudentPrice(int id, double price) {
        Student student = null;
        try {

            if (!studentRepo.existsById(id)) {
                throw new IllegalArgumentException(id + ":is not exist .");
            }

            student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException());
            student.setPrice(price + student.getPrice());
            studentRepo.save(student);

        } catch (Exception ee) {
            System.out.println("Error is :" + ee.getMessage());
        }
        return student;

    }

    @Override
    public void allStudentDelete() {
        studentRepo.deleteAll();
    }

    @Override
    public void studentDelete(int id) {
        try {
            if (!studentRepo.existsById(id)) {
                throw new IllegalArgumentException(id + "is not exists");
            }
            studentRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println("The error is :" + e.getMessage());
            throw new RuntimeException("This is failed to connect to server .");
        }
    }

    @Override
    public Student updatePassword(int id, String password) {
        Student student = null;
        try {
            if (!studentRepo.existsById(id)) {
                throw new IllegalArgumentException(id + "does not exists .");
            }
            int rowAffected = studentRepo.updatePassword(password, id);
            if (rowAffected > 0) {
                System.out.println("row is affected .");
            } else {
                System.out.println("Row is not affected .");
            }

        } catch (Exception e) {
            System.out.println("This error is :" + e.getMessage());
        }
        return student;
    }

}
