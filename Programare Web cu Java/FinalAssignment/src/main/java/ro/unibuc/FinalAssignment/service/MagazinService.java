package ro.unibuc.FinalAssignment.service;

import org.springframework.stereotype.Service;
import ro.unibuc.FinalAssignment.model.Magazin;
import ro.unibuc.FinalAssignment.repository.MagazinRepository;

@Service
public class MagazinService {
    private MagazinRepository magazinRepository;

    public MagazinService(MagazinRepository magazinRepository) {
        this.magazinRepository = magazinRepository;
    }

    public Magazin create(Magazin magazin){
        return magazinRepository.save(magazin);
    }

}
