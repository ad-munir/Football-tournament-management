package com.football.tournament.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "joueurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idJoueur;
    String nomJoueur;
    String poste;

    @ManyToOne
    Equipe equipe;
}
