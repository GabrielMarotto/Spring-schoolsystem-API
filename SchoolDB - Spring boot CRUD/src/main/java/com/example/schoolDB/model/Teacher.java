package com.example.schoolDB.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String subject;

    @ManyToMany
    @JoinTable(
            name = "teachers_in_classroom",
            joinColumns = @JoinColumn (name = "teacher_id"),
            inverseJoinColumns = @JoinColumn (name = "classroom_id"))
    private Set<Classroom> classrooms = new HashSet<>();

    public Teacher() {

    }

    public Teacher(Long id, String name, Integer age, String subject) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    public Teacher(String name, Integer age, String subject) {
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Set<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
