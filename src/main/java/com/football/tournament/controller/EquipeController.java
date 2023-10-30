package com.football.tournament.controller;

import com.football.tournament.entity.Equipe;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EquipeController {

    @Autowired
    EquipeRepository equipeRepo;

    @GetMapping("/equipes")
    public List<Equipe> getAllTeams() {
        return equipeRepo.findAll();
    }

    @PostMapping("/equipes")
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeRepo.save(equipe);
    }

    @PutMapping("/equipes/{id}")
    public Equipe updateEquipe(@RequestBody Equipe Equipe, @PathVariable Long id) {

        Optional<Equipe> optionalEquipe = equipeRepo.findById(id);
        if(optionalEquipe.isPresent()) {
            Equipe oldEquipe = optionalEquipe.get();
            oldEquipe.setNomEquipe(Equipe.getNomEquipe());
            oldEquipe.setPays(Equipe.getPays());
            return equipeRepo.save(oldEquipe);
        }

        throw new NotFoundException("Equipe Introuvable");
    }

    @DeleteMapping("/equipes/{id}")
    public void deleteEquipe(@PathVariable Long id) {

        Optional<Equipe> optionalEquipe = equipeRepo.findById(id);
        if(optionalEquipe.isPresent()) {
            equipeRepo.deleteById(id);
        }else {
            throw new NotFoundException("Equipe Introuvable");
        }
    }

    @GetMapping("/equipes/pays/{pays}")
    public List<Equipe> findAllByPays(@PathVariable String pays) {
        return equipeRepo.findAllByPays(pays);
    }

    // todo: get equipes by match id

}
