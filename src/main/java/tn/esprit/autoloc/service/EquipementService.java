package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.EquipementRepository;

@Service
@AllArgsConstructor
public class EquipementService implements IEquipementService{
    private final EquipementRepository equipementRepository;
}
