package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.DoHomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DoHomeworkJPA extends JpaRepository<DoHomework,Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from do_homework where homework_id=:id",nativeQuery = true)
    public void deleteByHomeworkId(@Param("id")Long homework_id);

    @Query(value = "select * from do_homework where homework_id=:id",nativeQuery = true)
    public Page<DoHomework> findByHomeworkId(@Param("id")Long homework_id, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update do_homework set score=:score,comment=:comment,marked=true where id=:id",nativeQuery = true)
    public void review(@Param("id")Long homework_id,@Param("score")int score,@Param("comment")String comment);

    @Query(value = "select * from do_homework where person_id=:id and homework_id=:homework_id",nativeQuery = true)
    public List<DoHomework> findByPersonIdAndHomeworkId(@Param("id")int person_id,@Param("homework_id")Long homework_id);

    @Query(value = "select count(*) from do_homework where homework_id=:id",nativeQuery = true)
    public int getDoHomeworkCount(@Param("id")Long homework_id);
}
