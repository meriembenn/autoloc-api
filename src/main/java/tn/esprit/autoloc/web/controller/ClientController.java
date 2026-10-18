package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Récupérer tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients renvoyée")
    @GetMapping
    public List<Client> getClients() {
        return clientService.retrieveAllClients();
    }

    @Operation(summary = "Récupérer un client par son id")
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        Client c = clientService.retrieveClient(clientId);
        if (c == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client introuvable");
        }
        return c;
    }

    @Operation(summary = "Ajouter un nouveau client")
    @ApiResponse(responseCode = "200", description = "Client ajouté")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PostMapping
    public Client addClient(@RequestBody Client c) {
        return clientService.addClient(c);
    }

    @Operation(summary = "Modifier un client existant (l'idClient doit être dans le JSON)")
    @ApiResponse(responseCode = "200", description = "Client modifié")
    @ApiResponse(responseCode = "400", description = "JSON invalide")
    @PutMapping
    public Client modifyClient(@RequestBody Client c) {
        return clientService.updateClient(c);
    }

    @Operation(summary = "Supprimer un client par son id")
    @ApiResponse(responseCode = "200", description = "Client supprimé")
    @DeleteMapping("/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }
}