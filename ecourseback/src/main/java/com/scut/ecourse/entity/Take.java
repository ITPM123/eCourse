package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "take")
public class Take {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "person_id",nullable = false)
    private PersonEntity student;
    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private CourseEntity course;
    @Column(name = "term")
    private String term;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonEntity getStudent() {
        return student;
    }

    public void setStudent(PersonEntity student) {
        this.student = student;
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
