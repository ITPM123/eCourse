package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PersonJPA
        extends JpaRepository<PersonEntity,Integer> {
    PersonEntity findByCode(String code);
    @Query(value = "select * from person where person_id in (select person_id from take where course_id=:id)",nativeQuery = true)
    List<PersonEntity> findAllStudentsById(@Param("id")long id);

    @Modifying
    @Transactional
    @Query(value = "delete from person where person_id=:personId",nativeQuery = true)
    public void delete(@Param("personId")int personId);
}
