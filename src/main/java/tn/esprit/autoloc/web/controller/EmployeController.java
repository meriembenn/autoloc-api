package tn.esprit.autoloc.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.service.IEmployeService;

@RestController
@RequestMapping("/employes")
@AllArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;
}