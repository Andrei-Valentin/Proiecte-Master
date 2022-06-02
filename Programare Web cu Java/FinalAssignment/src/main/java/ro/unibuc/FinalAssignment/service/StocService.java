package ro.unibuc.FinalAssignment.service;

import org.springframework.stereotype.Service;
import ro.unibuc.FinalAssignment.model.Stoc;
import ro.unibuc.FinalAssignment.repository.StocRepository;

@Service
public class StocService {
    private StocRepository stocRepository;

    public StocService(StocRepository stocRepository) {
        this.stocRepository = stocRepository;
    }

    public Stoc create(Stoc stoc) {


        return stocRepository.save(stoc);
    }
}
