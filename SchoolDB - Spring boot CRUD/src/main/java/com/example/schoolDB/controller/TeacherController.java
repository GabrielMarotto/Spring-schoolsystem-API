package com.example.schoolDB.controller;

import com.example.schoolDB.model.Teacher;
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
@RequestMapping (path = "teacher")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping
    public void addTeacher(@RequestBody Teacher teacher) {
            teacherService.addNewTeacher(teacher);
    }

    @PutMapping (path = "{teacherId}")
    public void updateTeacher (@PathVariable Long teacherId,
                          @RequestBody Teacher teacher) {
            teacherService.updateTeacher(teacherId,teacher.getName(),teacher.getAge(),teacher.getSubject());
    }

    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacherById(@PathVariable("teacherId") Long teacherId) {
            teacherService.deleteTeacherById(teacherId);
    }
}
