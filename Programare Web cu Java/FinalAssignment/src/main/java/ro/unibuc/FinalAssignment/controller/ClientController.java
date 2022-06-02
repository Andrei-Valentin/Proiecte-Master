package ro.unibuc.FinalAssignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.FinalAssignment.dto.clienti.CreateClientRequestDto;
import ro.unibuc.FinalAssignment.dto.clienti.UpdateClientRequestDto;
import ro.unibuc.FinalAssignment.exception.InvalidUpdateRequestException;
import ro.unibuc.FinalAssignment.mapper.ClientMapper;
import ro.unibuc.FinalAssignment.model.Client;
import ro.unibuc.FinalAssignment.service.ClientService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clienti")
@Validated
public class ClientController {
    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<Client> create(
            @Valid
            @RequestBody CreateClientRequestDto request){
        Client client = clientService.create(clientMapper.createClientRequestDtoToClient(request));

        return ResponseEntity.created(URI.create("/clienti/" + client.getId())).body(client);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(
            @PathVariable long id,
            @Valid
            @RequestBody UpdateClientRequestDto request){

        if(id != request.getId()){
            throw new InvalidUpdateRequestException();
        }

        return ResponseEntity.ok(clientService.update(clientMapper.updateClientRequestDtoToClient(request)));

    }
}
