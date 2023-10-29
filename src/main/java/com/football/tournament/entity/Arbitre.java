package com.football.tournament.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "arbitres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arbitre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idArbitre;
    String nom;
    String nationality;

    @OneToMany(mappedBy = "arbitre")
    @JsonIgnore
    List<Match> matches;
}
