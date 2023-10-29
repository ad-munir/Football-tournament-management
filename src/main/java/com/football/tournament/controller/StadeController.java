package com.football.tournament.controller;

import com.football.tournament.entity.Stade;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StadeController {

    @Autowired
    StadeRepository stadeRepo;

    @GetMapping("/stades")
    public List<Stade> getAllStades() {
        return stadeRepo.findAll();
    }

    @PostMapping("/stades")
    public Stade addStade(@RequestBody Stade stade) {
        return stadeRepo.save(stade);
    }

    @PutMapping("/stades/{id}")
    public Stade updateStade(@RequestBody Stade stade, @PathVariable Long id) {

        Optional<Stade> optionalStade = stadeRepo.findById(id);
        if(optionalStade.isPresent()) {
            Stade oldStade = optionalStade.get();
            oldStade.setNomStade(stade.getNomStade());
            oldStade.setVille(stade.getVille());
            return stadeRepo.save(oldStade);
        }

        throw new  NotFoundException("Stade Introuvable");
    }

    @DeleteMapping("/stades/{id}")
    public void deleteStade(@PathVariable Long id) {

        Optional<Stade> optionalStade = stadeRepo.findById(id);
        if(optionalStade.isPresent()) {
            stadeRepo.deleteById(id);
        }else {
            throw new NotFoundException("Stade Introuvable");
        }
    }


}
