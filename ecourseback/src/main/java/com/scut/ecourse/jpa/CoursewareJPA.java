package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CoursewareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CoursewareJPA extends JpaRepository<CoursewareEntity,Long> {
    @Modifying
    @Transactional
    @Query(value = "update courseware set visibility=:v where courseware_id=:i",nativeQuery = true)
    void modify(@Param("i")Long courseware_id,@Param("v")boolean visibility);
}
