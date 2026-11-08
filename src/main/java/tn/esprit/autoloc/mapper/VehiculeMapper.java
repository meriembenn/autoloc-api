package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDTO(Vehicule v) {
        if (v == null) {
            return null;
        }
        Long idAgence = (v.getAgence() != null) ? v.getAgence().getIdAgence() : null;
        return new VehiculeDTO(
                v.getIdVehicule(),
                v.getImmatriculation(),
                v.getMarque(),
                v.getModele(),
                v.getCategorie(),
                v.getTarifJournalier(),
                v.getStatut(),
                idAgence
        );
    }

    // Pour un AJOUT : crée un nouveau véhicule (l'agence est rattachée par le service)
    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) {
            return null;
        }
        Vehicule v = new Vehicule();
        v.setIdVehicule(dto.getIdVehicule());
        updateEntity(v, dto);
        return v;
    }

    // Pour une MODIFICATION : copie les champs du DTO sur un véhicule existant
    public void updateEntity(Vehicule v, VehiculeDTO dto) {
        v.setImmatriculation(dto.getImmatriculation());
        v.setMarque(dto.getMarque());
        v.setModele(dto.getModele());
        v.setCategorie(dto.getCategorie());
        v.setTarifJournalier(dto.getTarifJournalier());
        v.setStatut(dto.getStatut());
    }
}