package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.PaiementRepository;

@Service
@AllArgsConstructor
public class PaiementService implements IPaiementService{
    private final EquipementRepository equipementRepository;
}
