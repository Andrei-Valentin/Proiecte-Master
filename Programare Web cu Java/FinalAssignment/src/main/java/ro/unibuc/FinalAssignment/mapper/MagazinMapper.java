package ro.unibuc.FinalAssignment.mapper;

import org.springframework.stereotype.Component;
import ro.unibuc.FinalAssignment.dto.magazin.CreateMagazinRequestDto;
import ro.unibuc.FinalAssignment.model.Magazin;

@Component
public class MagazinMapper {
    public Magazin createMagazinRequestDtoToMagazin(CreateMagazinRequestDto request){
        return new Magazin(request.getMagazin_denumire(), request.getAdresa());
    }
}
