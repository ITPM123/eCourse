package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.HomeworkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeworkJPA extends JpaRepository<HomeworkEntity,Long> {
    @Query(value = "select * from homework where course_id=:id",nativeQuery = true)
    public Page<HomeworkEntity> findByCourseId(@Param("id")Long course_id, Pageable pageable);
}
