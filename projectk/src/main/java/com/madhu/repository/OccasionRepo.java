package com.madhu.repository;

import com.madhu.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionRepo extends JpaRepository<Occasion, Integer> {
}
