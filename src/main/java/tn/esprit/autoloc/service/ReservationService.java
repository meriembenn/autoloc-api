package tn.esprit.autoloc.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.ReservationRepository;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{
    private final ReservationRepository reservationRepository;
}
