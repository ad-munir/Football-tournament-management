package com.football.tournament.repository;

import com.football.tournament.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {

    List<Joueur> findAllByEquipe_NomEquipe(String nomEquipe);

    List<Joueur> findAllByPosteAndEquipe_NomEquipe(String poste, String nomEquipe);
}
