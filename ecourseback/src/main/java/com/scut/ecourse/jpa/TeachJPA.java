package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachJPA extends JpaRepository<Teach,Integer> {
    Teach findByTeacherAndCourseAndTerm(PersonEntity teacher, CourseEntity course, String term);
    Teach findByTeacherAndCourse(PersonEntity teacher,CourseEntity course);
}
