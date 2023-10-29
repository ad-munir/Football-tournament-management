package com.football.tournament.controller;

import com.football.tournament.entity.Arbitre;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.ArbitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArbitreController {

    @Autowired
    ArbitreRepository arbitreRepo;

    @GetMapping("/arbitres")
    public List<Arbitre> getAllArbitres() {
        return arbitreRepo.findAll();
    }

    @PostMapping("/arbitres")
    public Arbitre addArbitre(@RequestBody Arbitre arbitre) {
        return arbitreRepo.save(arbitre);
    }

    @PutMapping("/arbitres/{id}")
    public Arbitre updateArbitre(@RequestBody Arbitre arbitre, @PathVariable Long id) {

        Optional<Arbitre> optionalArbitre = arbitreRepo.findById(id);
        if(optionalArbitre.isPresent()) {
            Arbitre oldArbitre = optionalArbitre.get();
            oldArbitre.setNom(arbitre.getNom());
            oldArbitre.setNationality(arbitre.getNationality());
            return arbitreRepo.save(oldArbitre);
        }

        throw new  NotFoundException("Arbitre Introuvable");
    }

    @DeleteMapping("/arbitres/{id}")
    public void deleteArbitre(@PathVariable Long id) {

        Optional<Arbitre> optionalArbitre = arbitreRepo.findById(id);
        if(optionalArbitre.isPresent()) {
            arbitreRepo.deleteById(id);
        }else {
            throw new NotFoundException("Arbitre Introuvable");
        }
    }


}
