package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Récupérer tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients renvoyée")
    @GetMapping
    public List<ClientDTO> getClients() {
        return clientService.retrieveAllClients();
    }

    @Operation(summary = "Récupérer un client par son id")
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{client-id}")
    public ClientDTO retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.retrieveClient(clientId);
    }

    @Operation(summary = "Ajouter un nouveau client")
    @ApiResponse(responseCode = "200", description = "Client ajouté")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PostMapping
    public ClientDTO addClient(@Valid @RequestBody ClientDTO dto) {
        return clientService.addClient(dto);
    }

    @Operation(summary = "Modifier un client existant (l'idClient doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Client modifié")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PutMapping
    public ClientDTO modifyClient(@Valid @RequestBody ClientDTO dto) {
        return clientService.updateClient(dto);
    }

    @Operation(summary = "Supprimer un client par son id")
    @ApiResponse(responseCode = "200", description = "Client supprimé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @DeleteMapping("/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {

        clientService.removeClient(clientId);
    }
}