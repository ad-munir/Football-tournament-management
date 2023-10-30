package com.football.tournament.controller;

import com.football.tournament.entity.Equipe;
import com.football.tournament.entity.Joueur;
import com.football.tournament.entity.Match;
import com.football.tournament.entity.Stade;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.EquipeRepository;
import com.football.tournament.repository.JoueurRepository;
import com.football.tournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MatchController {

    @Autowired MatchRepository matchRepo;

    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }


    //todo: IDs to be validated
    @PostMapping("/matches")
    public Match addMatch(
            @RequestBody Match match
//            @RequestBody Long idArbitre,
//            @RequestBody Long idStade,
//            @RequestBody Long idEquipe1,
//            @RequestBody Long idEquipe2
    ) {

        return matchRepo.save(match);
    }


    //todo: IDs to be validated
    @PutMapping("/matches/{id}")
    public Match updateMatch(@RequestBody Match match, @PathVariable Long id) {

        Optional<Match> optionalMatch = matchRepo.findById(id);
        if(optionalMatch.isPresent()) {
            Match oldMatch = optionalMatch.get();
            oldMatch.setDateMatch(match.getDateMatch());
            oldMatch.setHeureMatch(match.getHeureMatch());
            oldMatch.setArbitre(match.getArbitre());
            oldMatch.setStade(match.getStade());
            oldMatch.setEquipes(match.getEquipes());
            return matchRepo.save(oldMatch);
        }

        throw new  NotFoundException("Match Introuvable!!");
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


    @GetMapping("/matches/{idMatch}/equipes")
    public List<Equipe> getEquipesByMatch(@PathVariable Long idMatch) {
        Optional<Match> optionalMatch = matchRepo.findById(idMatch);
        if(optionalMatch.isPresent()) {
            Match match = optionalMatch.get();
            return match.getEquipes();
        }
        throw new NotFoundException("Match Introuvable");
    }

    @GetMapping("/matches/getBy")
    public List<Match> getMatchByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

        if (date == null){
            throw new NotFoundException("Date not valid");
        }
        return matchRepo.findByDateMatch(date);
    }

    @GetMapping("/matches/{idMatch}/stade")
    public Stade getMatchStade(@PathVariable Long idMatch) {

        Optional<Match> optionalMatch = matchRepo.findById(idMatch);
        if(optionalMatch.isPresent()) {
            Match match = optionalMatch.get();
            return match.getStade();
        }
        throw new NotFoundException("Match Introuvable");
    }


    @DeleteMapping("/matches/played")
    public ResponseEntity<String> deletePlayedMatches() {
        Date currentDate = new Date();
        Time currentTime = new Time(currentDate.getTime());

        List<Match> playedMatches = matchRepo.findByDateMatchLessThanAndHeureMatchLessThan(currentDate,currentTime);

        playedMatches.forEach( match -> matchRepo.delete(match));
        return ResponseEntity.ok(playedMatches.size() + " matches has been deleted!");

    }
    @DeleteMapping("/matches/all")
    public void deleteAllMatchs() {
        matchRepo.deleteAll();
    }
}
