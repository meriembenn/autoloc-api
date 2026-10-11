package tn.esprit.autoloc.service;

import tn.esprit.autoloc.repository.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmployeService implements IEmployeService{
    private final EmployeRepository employeRepository;
}
