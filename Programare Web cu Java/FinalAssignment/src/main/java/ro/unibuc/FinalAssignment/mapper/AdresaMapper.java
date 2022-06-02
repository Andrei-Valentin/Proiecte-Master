package ro.unibuc.FinalAssignment.mapper;

import org.springframework.stereotype.Component;
import ro.unibuc.FinalAssignment.dto.adresa.CreateAdresaRequestDto;
import ro.unibuc.FinalAssignment.model.Adresa;

@Component
public class AdresaMapper {
    public Adresa createAdresaRequestDtoToAdresa(CreateAdresaRequestDto request){
        return new Adresa(request.getAdresa_tara(), request.getAdresa_oras(), request.getAdresa_strada(), request.getAdresa_numarStrada());
    }
}
