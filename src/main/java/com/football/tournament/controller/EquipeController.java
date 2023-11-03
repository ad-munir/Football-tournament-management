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

        Equipe oldEquipe = equipeRepo.findById(id)
                .orElseThrow( () -> new NotFoundException("Equipe Introuvable"));


        oldEquipe.setNomEquipe(Equipe.getNomEquipe());
        oldEquipe.setPays(Equipe.getPays());

        return equipeRepo.save(oldEquipe);
    }

    @DeleteMapping("/equipes/{id}")
    public void deleteEquipe(@PathVariable Long id) {

        Equipe equipe = equipeRepo.findById(id)
                .orElseThrow( ()-> new NotFoundException("Equipe Introuvable"));

        equipeRepo.deleteById(id);
    }

    @GetMapping("/equipes/pays/{pays}")
    public List<Equipe> findAllByPays(@PathVariable String pays) {
        return equipeRepo.findAllByPays(pays);
    }

    // todo: get equipes by match id

}
