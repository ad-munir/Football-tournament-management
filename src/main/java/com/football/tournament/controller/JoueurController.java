package com.football.tournament.controller;

import com.football.tournament.entity.Equipe;
import com.football.tournament.entity.Joueur;
import com.football.tournament.exception.errors.NotFoundException;
import com.football.tournament.repository.EquipeRepository;
import com.football.tournament.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JoueurController {

    @Autowired
    JoueurRepository joueurRepo;
    @Autowired
    EquipeRepository equipeRepo;

    @GetMapping("/joueurs")
    public List<Joueur> getAllPlayers() {
        return joueurRepo.findAll();
    }

    @PostMapping("/joueurs/equipe/{idEquipe}")
    public Joueur addJoueur(@RequestBody Joueur joueur, @PathVariable Long idEquipe) {
        Optional<Equipe> optionalEquipe = equipeRepo.findById(idEquipe);
        if(!optionalEquipe.isPresent()){
            throw new NotFoundException("Equipe introuvable");
        }

        joueur.setEquipe(optionalEquipe.get());
        return joueurRepo.save(joueur);
    }

    @PutMapping("/joueurs/{id}")
    public Joueur updateJoueur(@RequestBody Joueur joueur, @PathVariable Long id) {

        Optional<Joueur> optionalJoueur = joueurRepo.findById(id);
        if(optionalJoueur.isPresent()) {
            Joueur oldJoueur = optionalJoueur.get();
            oldJoueur.setNomJoueur(joueur.getNomJoueur());
            oldJoueur.setPoste(joueur.getPoste());
            return joueurRepo.save(oldJoueur);
        }

        throw new  NotFoundException("Joueur Introuvable");
    }

    @DeleteMapping("/joueurs/{id}")
    public void deleteJoueur(@PathVariable Long id) {

        Optional<Joueur> optionalJoueur = joueurRepo.findById(id);
        if(optionalJoueur.isPresent()) {
            joueurRepo.deleteById(id);
        }else {
            throw new NotFoundException("Joueur Introuvable");
        }
    }

    //todo: handle errors + not tested Q7
    @GetMapping("/joueurs/equipe/{nomEquipe}")
    public List<Joueur> findAllJoueurByNomEquipe(@PathVariable String nomEquipe) {
        return joueurRepo.findAllByEquipe_NomEquipe(nomEquipe);
    }

    //todo: handle errors Q9
    @GetMapping("/joueurs/{poste}/{nomEquipe}")
    public List<Joueur> findAllJoueurByPosteAndNomEquipe(@PathVariable String poste, @PathVariable String nomEquipe) {
        return joueurRepo.findAllByPosteAndEquipe_NomEquipe(poste, nomEquipe);
    }

}
