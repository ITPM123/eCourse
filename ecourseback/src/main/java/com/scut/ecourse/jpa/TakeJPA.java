package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.Take;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TakeJPA extends JpaRepository<Take,Integer> {
    @Query(value = "select * from take where person_id=:id",nativeQuery = true)
    Page<Take> getCoursesById(@Param("id")int person_id, Pageable pageable);

    @Query(value = "select count(*) from take where course_id=:id",nativeQuery = true)
    int getCourseStudentCount(@Param("id")Long course_id);
}
