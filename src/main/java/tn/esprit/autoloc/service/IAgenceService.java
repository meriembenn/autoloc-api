package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.AgenceDTO;

import java.util.List;

public interface IAgenceService {
    List<AgenceDTO> retrieveAllAgences();
    AgenceDTO retrieveAgence(Long idAgence);
    AgenceDTO addAgence(AgenceDTO dto);
    AgenceDTO updateAgence(AgenceDTO dto);
    void removeAgence(Long idAgence);
}