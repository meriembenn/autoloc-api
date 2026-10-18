package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Récupérer tous les véhicules")
    @ApiResponse(responseCode = "200", description = "Liste des véhicules renvoyée")
    @GetMapping
    public List<Vehicule> getVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    @Operation(summary = "Récupérer un véhicule par son id")
    @ApiResponse(responseCode = "200", description = "Véhicule trouvé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @GetMapping("/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        Vehicule v = vehiculeService.retrieveVehicule(vehiculeId);
        if (v == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable");
        }
        return v;
    }

    @Operation(summary = "Ajouter un nouveau véhicule")
    @ApiResponse(responseCode = "200", description = "Véhicule ajouté")
    @ApiResponse(responseCode = "400", description = "JSON invalide (ex. valeur d'enum incorrecte)")
    @PostMapping
    public Vehicule addVehicule(@RequestBody Vehicule v) {
        return vehiculeService.addVehicule(v);
    }

    @Operation(summary = "Modifier un véhicule existant (l'idVehicule doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Véhicule modifié")
    @ApiResponse(responseCode = "400", description = "JSON invalide (ex. valeur d'enum incorrecte)")
    @PutMapping
    public Vehicule modifyVehicule(@RequestBody Vehicule v) {
        return vehiculeService.updateVehicule(v);
    }

    @Operation(summary = "Supprimer un véhicule par son id")
    @ApiResponse(responseCode = "200", description = "Véhicule supprimé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @DeleteMapping("/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        if (vehiculeService.retrieveVehicule(vehiculeId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Véhicule introuvable");
        }
        vehiculeService.removeVehicule(vehiculeId);
    }
}