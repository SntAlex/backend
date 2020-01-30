package ftc.shift.sample.services;

import ftc.shift.sample.models.President;
import ftc.shift.sample.repositories.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresidentService {
    private final PresidentRepository presidentRepository;

    @Autowired
    public PresidentService(PresidentRepository presidentRepository) {
        this.presidentRepository = presidentRepository;
    }

    public President getPresident() {
        return presidentRepository.getPresident();
    }
}
