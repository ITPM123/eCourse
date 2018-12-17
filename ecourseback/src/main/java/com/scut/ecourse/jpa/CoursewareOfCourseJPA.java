package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CoursewareOfCourse;
import com.scut.ecourse.entity.Take;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CoursewareOfCourseJPA extends JpaRepository<CoursewareOfCourse,Long> {
    @Query(value = "select * from courseware_of_course where course_id=:id",nativeQuery = true)
    Page<CoursewareOfCourse> findByCourseId(@Param("id")Long course_id, Pageable pageable);
    @Query(value = "select * from courseware_of_course natural join courseware where course_id=:id and visibility = 1",nativeQuery = true)
    Page<CoursewareOfCourse> findVisibleCoursewaresByCourseId(@Param("id")Long course_id, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "delete from courseware_of_course where courseware_id=:id",nativeQuery = true)
    public void deleteByCoursewareId(@Param("id")Long courseware_id);
}
