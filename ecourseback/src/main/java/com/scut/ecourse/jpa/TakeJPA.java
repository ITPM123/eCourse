package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;


public interface TakeJPA extends JpaRepository<Take,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from take where take.course_id=:courseId and take.term=:term and take.person_id=:studentId",nativeQuery = true)
    public void delete(@Param("courseId")long courseId,@Param("studentId")int studentId,@Param("term")String term);

    Take findByStudentAndCourseAndTerm(PersonEntity student, CourseEntity course, String term);
}
