package com.football.tournament.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idMatch;

    @Temporal(TemporalType.DATE)
    Date dateMatch;
    Time heureMatch;

    @ManyToOne
    Arbitre arbitre;

    @ManyToOne
    Stade stade;

    @ManyToMany
    @JoinTable(name = "equipe_match",
            joinColumns = @JoinColumn(name = "idMatch"),
            inverseJoinColumns = @JoinColumn(name = "idEquipe"))
    List<Equipe> equipes;
}
