package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long course_id;
    @Column(name = "person_id")
    private Long person_id;
    @Column(name = "name", length = 40)
    private String name;
    @Column(name = "credit")
    private int credit;
    @Column(name = "outline",length = 1000,columnDefinition = "Text")
    private String  outline;
    @Column(name = "overview",length = 2000,columnDefinition = "Text")
    private String overview;
    @Column(name = "teaching_goal",length = 500,columnDefinition = "Text")
    private String teaching_goal;
    @Column(name = "description",length = 2000,columnDefinition = "Text")
    private String description;
    @Column(name = "image_file_name",length = 100)
    private String image_file_name;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTeaching_goal() {
        return teaching_goal;
    }

    public void setTeaching_goal(String teaching_goal) {
        this.teaching_goal = teaching_goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
        this.image_file_name = image_file_name;
    }
}
