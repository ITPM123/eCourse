package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "teach")
public class Teach {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity teacher;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column(name = "term")
    private String term;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonEntity teacher) {
        this.teacher = teacher;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
