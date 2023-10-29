package com.football.tournament.repository;

import com.football.tournament.entity.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadeRepository extends JpaRepository<Stade,Long> {
}
