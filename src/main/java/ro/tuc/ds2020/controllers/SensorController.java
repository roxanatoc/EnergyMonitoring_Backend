package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/admin/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public ResponseEntity<List<SensorDTO>> getSensors() {
        List<SensorDTO> dtos = sensorService.findSensors();
        for (SensorDTO dto : dtos) {
            Link sensorLink = linkTo(methodOn(SensorController.class)
                    .getSensor(dto.getId())).withRel("sensor");
            dto.add(sensorLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody SensorDTO sensorDTO) {
        UUID sensorID = sensorService.insert(sensorDTO);
        return new ResponseEntity<>(sensorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable("id") UUID sensorID) {
        SensorDTO dto = sensorService.findSensorById(sensorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<SensorDTO> updateSensor(@RequestBody SensorDTO sensorDTO, @PathVariable("id") UUID sensorID) {
        SensorDTO dto = sensorService.update(sensorDTO, sensorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteSensor(@PathVariable("id") UUID sensorID) {
        sensorService.delete(sensorID);
        return new ResponseEntity<>(sensorID, HttpStatus.OK);
    }

}
