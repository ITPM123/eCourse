package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.Teach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeachJPA extends JpaRepository<Teach,Integer> {
    @Query(value = "select * from teach where person_id=:id",nativeQuery = true)
    Page<Teach> getCoursesById(@Param("id")int person_id, Pageable pageable);
}
