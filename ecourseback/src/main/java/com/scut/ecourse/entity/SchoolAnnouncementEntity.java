package com.scut.ecourse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "school_announcement")
@JsonIgnoreProperties(value = {"author"})
public class SchoolAnnouncementEntity {

    @Id
    @GeneratedValue
    @Column(name = "school_announcement_id")
    private int schoolAnnouncementId;
    @Column(name = "title")
    private String title;
    @Column(name = "content",columnDefinition = "TEXT")
    private String content;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name="person_id")
    private PersonEntity author;
    @Column(name = "create_time",length = 50)
    private String createTime;
    @Column(name = "attachment_1")
    private String attachment1;
    @Column(name = "attachment_2")
    private String attachment2;
    @Column(name = "attachment_3")
    private String attachment3;
    @Column(name = "fileName_1")
    private String fileName1;
    @Column(name = "fileName_2")
    private String fileName2;
    @Column(name = "fileName_3")
    private String fileName3;

    public int getSchoolAnnouncementId() {
        return schoolAnnouncementId;
    }

    public void setSchoolAnnouncementId(int schoolAnnouncementId) {
        this.schoolAnnouncementId = schoolAnnouncementId;
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

    public PersonEntity getAuthor() {
        return author;
    }

    public void setAuthor(PersonEntity author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(String attachment1) {
        this.attachment1 = attachment1;
    }

    public String getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(String attachment2) {
        this.attachment2 = attachment2;
    }

    public String getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(String attachment3) {
        this.attachment3 = attachment3;
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFileName3() {
        return fileName3;
    }

    public void setFileName3(String fileName3) {
        this.fileName3 = fileName3;
    }
}
