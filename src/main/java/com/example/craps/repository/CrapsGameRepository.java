package com.example.craps.repository;

import com.example.craps.entity.CrapsGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrapsGameRepository extends JpaRepository<CrapsGameEntity, Long> {
}
