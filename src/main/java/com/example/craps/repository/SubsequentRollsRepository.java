package com.example.craps.repository;

import com.example.craps.entity.SubsequentRollsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsequentRollsRepository extends JpaRepository<SubsequentRollsEntity, Long> {
//    @Query("SELECT sre.roll FROM subsequent_rolls sre WHERE sre.crapsGame.id = 3")
//    List<Integer> getAllRolls();
}
