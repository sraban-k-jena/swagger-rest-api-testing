package com.jt.student_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jt.student_rest.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password =?1 where s.id =?2")
    int updatePassword(String password, int id);
}
