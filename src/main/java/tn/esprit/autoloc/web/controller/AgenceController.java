package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Récupérer toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste des agences renvoyée")
    @GetMapping
    public List<AgenceDTO> getAgences() {
        return agenceService.retrieveAllAgences();
    }

    @Operation(summary = "Récupérer une agence par son id")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{agence-id}")
    public AgenceDTO retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceService.retrieveAgence(agenceId);
    }

    @Operation(summary = "Ajouter une nouvelle agence")
    @ApiResponse(responseCode = "200", description = "Agence ajoutée")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PostMapping
    public AgenceDTO addAgence(@Valid @RequestBody AgenceDTO dto) {
        return agenceService.addAgence(dto);
    }

    @Operation(summary = "Modifier une agence existante (l'idAgence doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PutMapping
    public AgenceDTO modifyAgence(@Valid @RequestBody AgenceDTO dto) {
        return agenceService.updateAgence(dto);
    }

    @Operation(summary = "Supprimer une agence par son id")
    @ApiResponse(responseCode = "200", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.removeAgence(agenceId);
    }
}