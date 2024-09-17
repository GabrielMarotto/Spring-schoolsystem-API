package com.example.schoolDB.controller;

import com.example.schoolDB.model.Classroom;
import com.example.schoolDB.service.ClassroomService;
import com.example.schoolDB.service.TeacherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "classroom")
public class ClassroomController {

    private ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getClassrooms() {
        return classroomService.getClassrooms();
    }

    @PostMapping
    public void addClassroom(@RequestBody Classroom classroom) {
            classroomService.addNewClassroom(classroom);
    }

    @PutMapping(path = "{classroomId}")
    public void updateClassroom(@PathVariable("classroomId") Long classroomId,
                                @RequestBody Classroom classroom) {
            classroomService.updateClassroom(classroomId,classroom.getName(),classroom.getSize(), classroom.getTeachers(), classroom.getStudents());
    }

    @DeleteMapping(path = "{classroomId}")
    public void deleteClassroomById(@PathVariable("classroomId") Long classroomId) {
            classroomService.deleteClassroomById(classroomId);
    }
}