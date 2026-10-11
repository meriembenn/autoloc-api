package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.MaintenanceRepository;

@Service
@AllArgsConstructor
public class MaintenanceService implements IMaintenanceService{
    private final MaintenanceRepository maintenanceRepository;
}
