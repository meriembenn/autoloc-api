package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Récupérer toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste des agences renvoyée")
    @GetMapping
    public List<Agence> getAgences() {
        return agenceService.retrieveAllAgences();
    }

    @Operation(summary = "Récupérer une agence par son id")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        Agence a = agenceService.retrieveAgence(agenceId);
        if (a == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agence introuvable");
        }
        return a;
    }

    @Operation(summary = "Ajouter une nouvelle agence")
    @ApiResponse(responseCode = "200", description = "Agence ajoutée")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PostMapping
    public Agence addAgence(@RequestBody Agence a) {
        return agenceService.addAgence(a);
    }

    @Operation(summary = "Modifier une agence existante (l'idAgence doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PutMapping
    public Agence modifyAgence(@RequestBody Agence a) {
        return agenceService.updateAgence(a);
    }

    @Operation(summary = "Supprimer une agence par son id")
    @ApiResponse(responseCode = "200", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        if (agenceService.retrieveAgence(agenceId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agence introuvable");
        }
        agenceService.removeAgence(agenceId);
    }
}