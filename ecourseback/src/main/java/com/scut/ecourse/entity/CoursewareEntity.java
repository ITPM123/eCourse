package com.scut.ecourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "courseware")
@JsonIgnoreProperties(value = {"password"})
public class CoursewareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseware_id")
    private Long courseware_id;
    @Column(name = "name",length = 40)
    private String name;
    @Column(name = "courseware_name",length = 100)
    private String courseware_name;
    @Column(name = "file_name",length = 100)
    private String file_name;
    @Column(name = "visibility")
    private boolean visibility;
    @Column(name = "upload_date")
    private Date upload_date;

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    public String getCourseware_name() {
        return courseware_name;
    }

    public void setCourseware_name(String courseware_name) {
        this.courseware_name = courseware_name;
    }

    public Long getCourseware_id() {
        return courseware_id;
    }

    public void setCourseware_id(Long courseware_id) {
        this.courseware_id = courseware_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
