package com.football.tournament.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "equipes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idEquipe;
    String nomEquipe;
    String pays;

    @OneToMany(mappedBy = "equipe")
    @JsonIgnore
    List<Joueur> joueurs;

    @ManyToMany(mappedBy = "equipes")
    @JsonIgnore
    List<Match> matches;
}
