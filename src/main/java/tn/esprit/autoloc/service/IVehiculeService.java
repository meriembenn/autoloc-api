package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;

public interface IVehiculeService {
    List<VehiculeDTO> retrieveAllVehicules();
    VehiculeDTO addVehicule(VehiculeDTO dto);
    VehiculeDTO updateVehicule(VehiculeDTO dto);
    VehiculeDTO retrieveVehicule(Long idVehicule);
    void removeVehicule(Long idVehicule);
}