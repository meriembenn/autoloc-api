package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    List<Agence> retrieveAllAgences();
    Agence retrieveAgence(Long idAgence);
    Agence addAgence(Agence a);
    Agence updateAgence(Agence a);
    void removeAgence(Long idAgence);
}