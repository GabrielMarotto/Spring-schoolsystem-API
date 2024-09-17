package com.example.schoolDB.repository;

import com.example.schoolDB.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
