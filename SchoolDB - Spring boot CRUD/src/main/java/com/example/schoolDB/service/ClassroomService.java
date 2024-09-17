package com.example.schoolDB.service;

import com.example.schoolDB.model.Classroom;
import com.example.schoolDB.model.Student;
import com.example.schoolDB.model.Teacher;
import com.example.schoolDB.repository.ClassroomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassroomService {

    private ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    public void addNewClassroom(@RequestBody Classroom classroom) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroom.getId());
        if (classroomOptional.isEmpty()) {
            classroomRepository.save(classroom);
        } else {
            throw new IllegalStateException("Classroom with id " + classroom.getId() + " already exists");
        }
    }

    @Transactional
    public void updateClassroom(Long classroomId, String name, Integer size, Set<Teacher> teachers, Set<Student> students) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() ->
                new IllegalStateException(
                        "Classroom with id " + classroomId + "does not exist")
                );

        if (!name.isEmpty() && !Objects.equals(classroom.getName(), name)) {
            classroom.setName(name);
        }

        if (size != null && size > 0) {
            classroom.setSize(size);
        }

        if (!teachers.isEmpty() && !Objects.equals(classroom.getTeachers(), teachers)) {
            classroom.setTeachers(teachers);
        }

        if (!students.isEmpty() && !Objects.equals(classroom.getStudents(), students)) {
            classroom.setStudents(students);
        }
    }

    public void deleteClassroomById(Long classroomId) {
        boolean exists = classroomRepository.existsById(classroomId);
        if (!exists) {
            throw new IllegalStateException("Classroom with id " + classroomId + "does not exist");
        } else {
            classroomRepository.deleteById(classroomId);
        }
    }
}
