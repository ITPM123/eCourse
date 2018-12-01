package com.scut.ecourse.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "do_homework")
public class DoHomework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "homework_id")
    private HomeworkEntity homework;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @Column(name = "score")
    private int score;
    @Column(name = "submit_time")
    private Date submit_time;
    @Column(name = "attachment",length = 100)
    private String attachment;
    @Column(name = "attachment_filename",length = 100)
    private String attachment_filename;
    @Column(name = "marked")
    private boolean marked;
    @Column(name = "content",length = 2000,columnDefinition = "Text")
    private String content;
    @Column(name = "comment",length = 2000,columnDefinition = "Text")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeworkEntity getHomework() {
        return homework;
    }

    public void setHomework(HomeworkEntity homework) {
        this.homework = homework;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAttachment_filename() {
        return attachment_filename;
    }

    public void setAttachment_filename(String attachment_filename) {
        this.attachment_filename = attachment_filename;
    }
}
