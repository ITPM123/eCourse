package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CourseJPA
        extends JpaRepository<CourseEntity,Long>, JpaSpecificationExecutor<CourseEntity>, Serializable
{
}
