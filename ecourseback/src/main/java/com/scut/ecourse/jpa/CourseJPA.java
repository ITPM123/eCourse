package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface CourseJPA
        extends JpaRepository<CourseEntity,Long>, JpaSpecificationExecutor<CourseEntity>, Serializable
{

}
