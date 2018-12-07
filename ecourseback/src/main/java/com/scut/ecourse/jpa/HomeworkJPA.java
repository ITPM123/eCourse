package com.scut.ecourse.jpa;

import com.scut.ecourse.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkJPA extends JpaRepository<HomeworkEntity,Long> {
}
