package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.SchoolAnnouncementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolAnnouncementJPA
        extends JpaRepository<SchoolAnnouncementEntity,Integer> {

    @Query(value = "select * from school_announcement where person_id=:id",nativeQuery = true)
    Page<SchoolAnnouncementEntity> findByAuthorId(@Param("id")long authorId, Pageable pageable);
}
