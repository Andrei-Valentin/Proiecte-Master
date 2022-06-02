package ro.unibuc.FinalAssignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.FinalAssignment.dto.stoc.CreateStocRequestDto;
import ro.unibuc.FinalAssignment.mapper.StocMapper;
import ro.unibuc.FinalAssignment.model.Stoc;
import ro.unibuc.FinalAssignment.service.StocService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/stoc")
@Validated
public class StocController {
    private StocService stocService;
    private StocMapper stocMapper;

    public StocController(StocService stocService, StocMapper stocMapper) {
        this.stocService = stocService;
        this.stocMapper = stocMapper;
    }

    public ResponseEntity<Stoc> create(
            @Valid
            @RequestBody CreateStocRequestDto request){

        Stoc stoc = stocService.create(stocMapper.CreateStocRequestDtoToStoc(request));

        return ResponseEntity.created(URI.create("/stoc/" + stoc.getId())).body(stoc);
    }
}
