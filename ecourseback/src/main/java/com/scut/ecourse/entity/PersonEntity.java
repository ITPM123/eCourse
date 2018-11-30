package com.scut.ecourse.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private int personId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "sex",length = 1)
    private int sex;//0为男，1为女，3为不公开
    @Column(name = "school")
    private String school;//学院
    @Column(name = "role",length = 1)
    private int role;//0为教务员，1为教师，2为学生
    @Column(name = "real_name")
    private String realName;
    @Column(name = "code")
    private String code;//教工号/学号
    @Column(name = "title")
    private String title;//职称
    @Column(name = "major")
    private String major;
    @Column(name = "grade")
    private String grade;
    @Column(name = "class")
    private String classs;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "motto")
    private String motto;//座右铭

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
