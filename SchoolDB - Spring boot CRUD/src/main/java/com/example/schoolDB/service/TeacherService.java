package com.example.schoolDB.service;

import com.example.schoolDB.model.Teacher;
import com.example.schoolDB.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService {

    public TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher (Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacher.getId());
        if (teacherOptional.isEmpty()) {
            teacherRepository.save(teacher);
        } else {
            throw new IllegalStateException("Teacher already exists");
        }
    }

    @Transactional
    public void updateTeacher(Long teacherId, String name, Integer age, String subject) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new IllegalStateException(
                "Teacher with id " + teacherId + "does not exist"
        ));

        if (!name.isEmpty() && !Objects.equals(teacher.getName(), name)) {
            teacher.setName(name);
        }

        if (age !=null && age > 0) {
            teacher.setAge(age);
        }

        if (!subject.isEmpty()  && !Objects.equals(teacher.getSubject(), subject)) {
            teacher.setSubject(subject);
        }
    }

    public void deleteTeacherById(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);
        if (!exists) {
            throw new IllegalStateException("Teacher with id " + teacherId + "does not exist");
        } else {
            teacherRepository.deleteById(teacherId);
        }

    }

}
