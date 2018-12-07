package com.scut.ecourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "course_announcement")
@JsonIgnoreProperties(value = {"course"})
public class CourseAnnouncementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_announcement_id")
    private int courseAnnouncementId;
    @Column(name = "title")
    private String title;
    @Column(name = "content",columnDefinition = "Text")
    private String content;
    @Column(name = "create_time",length = 50)
    private String createTime;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private CourseEntity course;

    public int getCourseAnnouncementId() {
        return courseAnnouncementId;
    }

    public void setCourseAnnouncementId(int courseAnnouncementId) {
        this.courseAnnouncementId = courseAnnouncementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }
}
