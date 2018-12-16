package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public interface CourseJPA
        extends JpaRepository<CourseEntity,Long>, JpaSpecificationExecutor<CourseEntity>, Serializable {
    @Query(value = "select * from course where person_id =  :id",nativeQuery = true)
    public List<CourseEntity> getCoursesByPersonId(@Param("id")Long id);
}
