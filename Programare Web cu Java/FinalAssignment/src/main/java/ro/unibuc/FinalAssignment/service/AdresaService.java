package ro.unibuc.FinalAssignment.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.*;
import ro.unibuc.FinalAssignment.model.Adresa;
import ro.unibuc.FinalAssignment.exception.AdresaAlreadyExistsException;
import ro.unibuc.FinalAssignment.repository.AdresaRepository;

import java.util.Optional;

@Service
public class AdresaService {
    private AdresaRepository adresaRepository;

    public AdresaService(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    public Adresa create(Adresa adresa) {
        //Optional <Adresa> existingAdresa  = adresaRepository.findByEntry(adresa.getAdresa_tara(),adresa.getAdresa_oras(),adresa.getAdresa_strada(),adresa.getAdresa_numarStrada());
        //if(existingAdresa.isPresent()){
        //    throw new AdresaAlreadyExistsException();
        //}

        return adresaRepository.save(adresa);
    }
}
