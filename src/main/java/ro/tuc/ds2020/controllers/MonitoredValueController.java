package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.entities.SensorValue;
import ro.tuc.ds2020.services.MonitoredValueService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/monitoredValue")
public class MonitoredValueController {

    private final MonitoredValueService monitoredValueService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MonitoredValueController(MonitoredValueService monitoredValueService, SimpMessagingTemplate simpMessagingTemplate) {
        this.monitoredValueService = monitoredValueService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/values")
    public ResponseEntity<List<MonitoredValueDTO>> getValues() {
        List<MonitoredValueDTO> dtos = monitoredValueService.findMonitoredValues();
        for (MonitoredValueDTO dto : dtos) {
            Link valuesLink = linkTo(methodOn(MonitoredValueController.class)
                    .getMonitoredValue(dto.getId())).withRel("monitoredValue");
            dto.add(valuesLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MonitoredValueDTO> getMonitoredValue(@PathVariable("id") UUID id) {
        MonitoredValueDTO dto = monitoredValueService.findMonitoredValueById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public void sendValue(SensorValue sensorValue)   {
        this.simpMessagingTemplate.convertAndSend("/topic/monitoredValue", sensorValue);
    }

    @PostMapping("/message")
    public String sendNotification(String notification)   {
        //this.simpMessagingTemplate.convertAndSend("Maximum value exceeded!");
        return "Maximum value exceeded!";
    }

   /*
    @GetMapping()
    public ResponseEntity<List<MonitoredValueDTO>> getValues() {
        List<MonitoredValueDTO> dtos = monitoredValueService.findMonitoredValues();
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
    */

    @PostMapping()
    public ResponseEntity<UUID> insert(@Valid @RequestBody MonitoredValueDTO monitoredValueDTO) {
        UUID id = monitoredValueService.insert(monitoredValueDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
