package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJPA
        extends JpaRepository<PersonEntity,Integer> {
}
