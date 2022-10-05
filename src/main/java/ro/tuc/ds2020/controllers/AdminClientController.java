package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.services.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/admin/client")
public class AdminClientController {

    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> dtos = clientService.findClients();
        for (ClientDTO dto : dtos) {
            Link clientLink = linkTo(methodOn(AdminClientController.class)
                    .getClient(dto.getId())).withRel("client");
            dto.add(clientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertClient(@Valid @RequestBody ClientDTO clientDTO) {
        System.out.println(clientDTO.getBirthdate() + " " + clientDTO.getId());
        UUID clientID = clientService.insert(clientDTO);
        return new ResponseEntity<>(clientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") UUID clientID) {
        ClientDTO dto = clientService.findClientById(clientID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /*
    @GetMapping(value = "/{username}")
    public ResponseEntity<ClientDTO> getClientByUsername(@PathVariable("username") String username) {
        ClientDTO dto = clientService.findClientByUsername(username);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }*/

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable("id") UUID clientID) {
        ClientDTO dto = clientService.update(clientDTO, clientID);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteClient(@PathVariable("id") UUID clientID) {
        clientService.delete(clientID);
        return new ResponseEntity<>(clientID, HttpStatus.OK);
    }

}
