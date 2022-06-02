package ro.unibuc.FinalAssignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.FinalAssignment.dto.magazin.CreateMagazinRequestDto;
import ro.unibuc.FinalAssignment.mapper.MagazinMapper;
import ro.unibuc.FinalAssignment.model.Magazin;
import ro.unibuc.FinalAssignment.service.MagazinService;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/magazine")
@Validated
public class MagazinController {

    private MagazinService magazinService;
    private MagazinMapper magazinMapper;

    public MagazinController(MagazinService magazinService, MagazinMapper magazinMapper) {
        this.magazinService = magazinService;
        this.magazinMapper = magazinMapper;
    }

    @PostMapping
    public ResponseEntity<Magazin> create(
            @Valid
            @RequestBody CreateMagazinRequestDto request){
        Magazin magazin = magazinService.create(magazinMapper.createMagazinRequestDtoToMagazin(request));

        return ResponseEntity.created(URI.create("/magazine/" + magazin.getId())).body(magazin);

    }


}

