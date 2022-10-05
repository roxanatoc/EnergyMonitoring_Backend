package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.builders.ClientBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public List<ClientDTO> findClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(ClientBuilder::toClientDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findClientById(UUID id) {
        Optional<Client> prosumerOptional = clientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + id);
        }
        return ClientBuilder.toClientDTO(prosumerOptional.get());
    }

    public ClientDTO findClientByUsername(String username) {
        Optional<Client> prosumerOptional = clientRepository.findSeniorsByName(username);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Client with username {} was not found in db", username);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with username: " + username);
        }
        return ClientBuilder.toClientDTO(prosumerOptional.get());
    }

    public UUID insert(ClientDTO clientDTO) {
        Client client = ClientBuilder.toEntity(clientDTO);
        System.out.println(client);
        client = clientRepository.save(client);
        LOGGER.debug("Client with id {} was inserted in db", client.getId());
        return client.getId();
    }

    public ClientDTO update(ClientDTO clientDTO, UUID clientID) {
        Optional<Client> clientOptional = clientRepository.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", clientID);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + clientID);
        }
        Client client = ClientBuilder.toEntity(clientDTO);
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setBirthdate(clientDTO.getBirthdate());
        clientRepository.save(client);
        return clientDTO;
    }

    public void delete(UUID id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + id);
        }
        clientRepository.deleteById(id);
        LOGGER.debug("Client with id {} was deleted from the db", id);
    }

}
