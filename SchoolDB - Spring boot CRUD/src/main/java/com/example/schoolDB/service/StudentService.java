package com.example.schoolDB.service;

import com.example.schoolDB.model.Student;
import com.example.schoolDB.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

   private StudentRepository studentRepository;

   @Autowired
   public StudentService(StudentRepository studentRepository) {
       this.studentRepository = studentRepository;
   }

   public List<Student> getStudents() {
       return studentRepository.findAll();
   }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isEmpty()) {
            studentRepository.save(student);
        } else {
            throw new IllegalStateException("Student with id " + student.getId() + " already exists");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, Integer age, String name, String email) {
       Student student = studentRepository.findById(studentId).orElseThrow(() ->
               new IllegalStateException("Student with id " + studentId + " does not exist"));

       if (!name.isEmpty() && !Objects.equals(student.getName(), name)) {
           student.setName(name);
       }

        if (age!= null && age > 0) {
            student.setAge(age);
        }

        if (!email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }

    }

    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if (!exists) {
           throw new IllegalStateException("Student with id " + studentId + "does not exist");
       } else {
           studentRepository.deleteById(studentId);
       }
    }
}
