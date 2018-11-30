package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJPA
        extends JpaRepository<CourseEntity,Long> {
}
