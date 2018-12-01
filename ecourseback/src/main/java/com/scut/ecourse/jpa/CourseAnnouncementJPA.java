package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseAnnouncementEntity;
import com.scut.ecourse.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseAnnouncementJPA
        extends JpaRepository<CourseAnnouncementEntity,Integer> {

    @Query(value = "select * from course_announcement where course_id=:id",nativeQuery = true)
    List<CourseAnnouncementEntity> findByCourseId(@Param("id")long courseId);
}
