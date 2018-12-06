package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "courseware")
public class CoursewareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseware_id")
    private Long courseware_id;
    @Column(name = "name",length = 40)
    private String name;
    @Column(name = "courseware",length = 100)
    private String courseware;
    @Column(name = "file_name",length = 100)
    private String file_name;
    @Column(name = "visibility")
    private boolean visibility;


    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
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
