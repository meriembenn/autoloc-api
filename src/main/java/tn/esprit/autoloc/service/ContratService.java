package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.ContratRepository;

@Service
@AllArgsConstructor
public class ContratService implements IContratService{
    private final ContratRepository contratRepository;
}
