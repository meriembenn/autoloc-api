package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
@Service
@AllArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public List<VehiculeDTO> retrieveAllVehicules() {
        return vehiculeRepository.findAll()
                .stream()
                .map(vehiculeMapper::toDTO)
                .toList();
    }

    @Override
    public VehiculeDTO retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .map(vehiculeMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable : id " + idVehicule));
    }

    @Override
    public VehiculeDTO addVehicule(VehiculeDTO dto) {
        Vehicule v = vehiculeMapper.toEntity(dto);
        v.setAgence(chercherAgence(dto.getIdAgence()));
        return vehiculeMapper.toDTO(vehiculeRepository.save(v));
    }

    @Override
    public VehiculeDTO updateVehicule(VehiculeDTO dto) {
        if (dto.getIdVehicule() == null) {
            throw new ResourceNotFoundException("Véhicule introuvable : id manquant");
        }
        Vehicule existant = vehiculeRepository.findById(dto.getIdVehicule())
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable : id " + dto.getIdVehicule()));
        vehiculeMapper.updateEntity(existant, dto);
        existant.setAgence(chercherAgence(dto.getIdAgence()));
        return vehiculeMapper.toDTO(vehiculeRepository.save(existant));
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new ResourceNotFoundException("Véhicule introuvable : id " + idVehicule);
        }
        vehiculeRepository.deleteById(idVehicule);
    }

    private Agence chercherAgence(Long idAgence) {
        if (idAgence == null) {
            return null;
        }
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable : id " + idAgence));
    }
}