package com.football.tournament.repository;

import com.football.tournament.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {

    List<Equipe> findAllByPays(String pays);
}
