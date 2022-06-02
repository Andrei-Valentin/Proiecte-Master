package ro.unibuc.FinalAssignment.service;

import org.springframework.stereotype.*;
import ro.unibuc.FinalAssignment.exception.ClientAlreadyExistsException;
import ro.unibuc.FinalAssignment.exception.ClientEmailAlreadyExistsException;
import ro.unibuc.FinalAssignment.exception.ClientNotFoundException;
import ro.unibuc.FinalAssignment.model.Client;
import ro.unibuc.FinalAssignment.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {

        checkUniqueCNP(client);

        checkUniqueEmail(client);

        return clientRepository.save(client);
    }

    public Client update(Client client){
        Client existingClient = clientRepository.findById(client.getId())
                .orElseThrow(() -> new ClientNotFoundException());

        if(client.getCNP() != existingClient.getCNP()){
            checkUniqueCNP(client);
        }

        if(!client.getEmail().equals(existingClient.getEmail())){
            checkUniqueEmail(client);
        }

        return clientRepository.save(client);
    }

    private void checkUniqueCNP(Client client){
        Optional <Client> existingClient = clientRepository.findByCNP(client.getCNP());
        if(existingClient.isPresent()){
            throw new ClientAlreadyExistsException();
        }
    }

    private void checkUniqueEmail(Client client){
        Optional<Client> existingEmail  = clientRepository.findByEmail(client.getEmail());
        if(existingEmail.isPresent()){
            throw new ClientEmailAlreadyExistsException();
        }
    }
}
