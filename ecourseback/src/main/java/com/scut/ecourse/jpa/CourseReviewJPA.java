package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface CourseReviewJPA extends JpaRepository<CourseReview,Long> {
    @Query(value = "select course_id from course where course_id not in (select course_id from course_review)",nativeQuery = true)
    public List<BigInteger>getUnexaminedCourseId();
}
