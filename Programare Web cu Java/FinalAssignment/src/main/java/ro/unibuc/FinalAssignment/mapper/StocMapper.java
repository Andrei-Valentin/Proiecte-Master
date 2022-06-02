package ro.unibuc.FinalAssignment.mapper;

import org.springframework.stereotype.Component;
import ro.unibuc.FinalAssignment.dto.stoc.CreateStocRequestDto;
import ro.unibuc.FinalAssignment.model.Stoc;

@Component
public class StocMapper {
    public Stoc CreateStocRequestDtoToStoc(CreateStocRequestDto request){
        return new Stoc(request.getMagazin_id(), request.getProdus_id(), request.getCantitate_produs());
    }
}
