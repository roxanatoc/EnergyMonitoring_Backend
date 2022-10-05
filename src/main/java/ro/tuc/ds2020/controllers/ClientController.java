package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/client")
public class ClientController {

    private final UserService userService;

    @Autowired
    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUserClient(@PathVariable("username") String usernameClient) {
        UserDTO dto = userService.findByUsername(usernameClient);
        System.out.println(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
