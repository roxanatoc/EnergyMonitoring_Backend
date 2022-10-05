package ro.tuc.ds2020.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.services.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/login")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUserAdmin(@PathVariable("username") String usernameAdmin) {
        UserDTO dto = userService.findByUsername(usernameAdmin);
        System.out.println(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
