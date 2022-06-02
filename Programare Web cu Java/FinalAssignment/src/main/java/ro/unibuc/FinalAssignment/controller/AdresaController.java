package ro.unibuc.FinalAssignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.FinalAssignment.dto.adresa.CreateAdresaRequestDto;
import ro.unibuc.FinalAssignment.mapper.AdresaMapper;
import ro.unibuc.FinalAssignment.model.Adresa;
import ro.unibuc.FinalAssignment.service.AdresaService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/adresa")
@Validated
public class AdresaController {
    private AdresaService adresaService;
    private AdresaMapper adresaMapper;

    public AdresaController(AdresaService adresaService, AdresaMapper adresaMapper) {
        this.adresaService = adresaService;
        this.adresaMapper = adresaMapper;
    }

    @PostMapping
    public ResponseEntity<Adresa> create(
            @Valid
            @RequestBody CreateAdresaRequestDto request){
        Adresa adresa = adresaService.create(adresaMapper.createAdresaRequestDtoToAdresa(request));

        return ResponseEntity.created(URI.create("/adresa/" + adresa.getId())).body(adresa);

    }
}
