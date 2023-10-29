package com.football.tournament.repository;

import com.football.tournament.entity.Arbitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitreRepository extends JpaRepository<Arbitre, Long> {
}
