package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "courseware_od_course")
public class CoursewareOfCourse {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @ManyToOne
    @JoinColumn(name = "courseware_id")
    private CoursewareEntity courseware;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public CoursewareEntity getCourseware() {
        return courseware;
    }

    public void setCourseware(CoursewareEntity courseware) {
        this.courseware = courseware;
    }
}
