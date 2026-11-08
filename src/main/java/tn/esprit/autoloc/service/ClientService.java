package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.web.dto.ClientDTO;

import java.util.List;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> retrieveAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientDTO addClient(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(ClientDTO dto) {
        if (dto.getIdClient() == null || !clientRepository.existsById(dto.getIdClient())) {
            throw new ResourceNotFoundException("Client introuvable : id " + dto.getIdClient());
        }
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable : id " + idClient));
    }

    @Override
    public void removeClient(Long idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new ResourceNotFoundException("Client introuvable : id " + idClient);
        }
        clientRepository.deleteById(idClient);
    }

    @Override
    public List<ClientDTO> addClients(List<ClientDTO> dtos) {
        List<Client> clients = dtos.stream()
                .map(clientMapper::toEntity)
                .toList();
        return clientRepository.saveAll(clients)
                .stream()
                .map(clientMapper::toDTO)
                .toList();
    }
}