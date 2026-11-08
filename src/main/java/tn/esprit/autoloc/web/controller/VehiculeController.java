package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Récupérer tous les véhicules")
    @ApiResponse(responseCode = "200", description = "Liste des véhicules renvoyée")
    @GetMapping
    public List<VehiculeDTO> getVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    @Operation(summary = "Récupérer un véhicule par son id")
    @ApiResponse(responseCode = "200", description = "Véhicule trouvé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @GetMapping("/{vehicule-id}")
    public VehiculeDTO retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return vehiculeService.retrieveVehicule(vehiculeId);
    }

    @Operation(summary = "Ajouter un nouveau véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule ajouté")
    @ApiResponse(responseCode = "400", description = "JSON invalide (ex. valeur d'enum incorrecte)")
    @PostMapping
    public VehiculeDTO addVehicule(@Valid @RequestBody VehiculeDTO dto) {
        return vehiculeService.addVehicule(dto);
    }

    @Operation(summary = "Modifier un véhicule existant (l'idVehicule doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Véhicule modifié")
    @ApiResponse(responseCode = "400", description = "JSON invalide (ex. valeur d'enum incorrecte)")
    @PutMapping
    public VehiculeDTO modifyVehicule(@Valid @RequestBody VehiculeDTO dto) {
        return vehiculeService.updateVehicule(dto);
    }

    @Operation(summary = "Supprimer un véhicule par son id")
    @ApiResponse(responseCode = "200", description = "Véhicule supprimé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @DeleteMapping("/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        vehiculeService.removeVehicule(vehiculeId);
    }
}