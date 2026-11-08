package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.mapper.AgenceMapper;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class AgenceService implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public List<AgenceDTO> retrieveAllAgences() {
        return agenceRepository.findAll()
                .stream()
                .map(agenceMapper::toDTO)
                .toList();
    }

    @Override
    public AgenceDTO retrieveAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .map(agenceMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable : id " + idAgence));
    }

    @Override
    public AgenceDTO addAgence(AgenceDTO dto) {
        Agence agence = agenceMapper.toEntity(dto);
        return agenceMapper.toDTO(agenceRepository.save(agence));
    }

    @Override
    public AgenceDTO updateAgence(AgenceDTO dto) {
        if (dto.getIdAgence() == null || !agenceRepository.existsById(dto.getIdAgence())) {
            throw new ResourceNotFoundException("Agence introuvable : id " + dto.getIdAgence());
        }
        Agence agence = agenceMapper.toEntity(dto);
        return agenceMapper.toDTO(agenceRepository.save(agence));
    }

    @Override
    public void removeAgence(Long idAgence) {
        if (!agenceRepository.existsById(idAgence)) {
            throw new ResourceNotFoundException("Agence introuvable : id " + idAgence);
        }
        agenceRepository.deleteById(idAgence);
    }
}