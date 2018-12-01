package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachJPA extends JpaRepository<Teach,Integer> {
}
