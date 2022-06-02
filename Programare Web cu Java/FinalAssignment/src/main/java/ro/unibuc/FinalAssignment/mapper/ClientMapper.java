package ro.unibuc.FinalAssignment.mapper;

import org.springframework.stereotype.Component;
import ro.unibuc.FinalAssignment.dto.clienti.CreateClientRequestDto;
import ro.unibuc.FinalAssignment.dto.clienti.UpdateClientRequestDto;
import ro.unibuc.FinalAssignment.model.Client;

@Component
public class ClientMapper {
    public Client createClientRequestDtoToClient(CreateClientRequestDto request){
        return new Client(request.getClient_nume(), request.getClient_CNP(), request.getClient_varsta(), request.getClient_email());
    }

    public Client updateClientRequestDtoToClient(UpdateClientRequestDto request){
        return new Client(request.getId(), request.getClient_nume(), request.getClient_CNP(), request.getClient_varsta(), request.getClient_email());
    }
}
