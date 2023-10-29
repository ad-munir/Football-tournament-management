package com.football.tournament.controller;

import com.football.tournament.entity.Equipe;
import com.football.tournament.entity.Joueur;
import com.football.tournament.entity.Match;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.EquipeRepository;
import com.football.tournament.repository.JoueurRepository;
import com.football.tournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MatchController {

    @Autowired
    MatchRepository matchRepo;
    @Autowired
    EquipeRepository equipeRepo;

    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    @PostMapping("/matches")
    public Match addMatch(@RequestBody Match match) {
        return matchRepo.save(match);
    }

    @PutMapping("/matches/{id}")
    public Match updateMatch(@RequestBody Match match, @PathVariable Long id) {

        Optional<Match> optionalMatch = matchRepo.findById(id);
        if(optionalMatch.isPresent()) {
            Match oldMatch = optionalMatch.get();
            oldMatch.setDateMatch(match.getDateMatch());
            oldMatch.setHeureMatch(match.getHeureMatch());
            return matchRepo.save(oldMatch);
        }

        throw new  NotFoundException("Match Introuvable");
    }

    @DeleteMapping("/matches/{id}")
    public void deleteMatch(@PathVariable Long id) {

        Optional<Match> optionalMatch = matchRepo.findById(id);
        if(optionalMatch.isPresent()) {
            matchRepo.deleteById(id);
        }else {
            throw new NotFoundException("Match Introuvable");
        }
    }


}
