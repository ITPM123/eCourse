package com.scut.ecourse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homework")
public class HomeworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "homework_id")
    private Long homwwork_id;
    @Column(name = "homework_title",length = 40)
    private String homework_title;
    @Column(name = "description",length = 2000,columnDefinition = "Text")
    private String description;
    @Column(name = "release_time")
    private Date release_time;
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "attachment",length = 100)
    private String attachment;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    public Long getHomwwork_id() {
        return homwwork_id;
    }

    public void setHomwwork_id(Long homework_id) {
        this.homwwork_id = homwwork_id;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public void setHomework_title(String homework_title) {
        this.homework_title = homework_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public CourseEntity getCourse() {
        return course;
    }

    @JsonBackReference
    public void setCourse(CourseEntity course) {
        this.course = course;
    }
}
