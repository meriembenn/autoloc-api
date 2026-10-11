package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.AgenceRepository;

@Service
@AllArgsConstructor
public class AgenceService implements IAgenceService{
    private final AgenceRepository agenceRepository;
}
