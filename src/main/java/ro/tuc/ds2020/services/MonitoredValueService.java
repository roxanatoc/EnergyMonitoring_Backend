package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.dtos.builders.MonitoredValueBuilder;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.repositories.MonitoredValueRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MonitoredValueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredValueService.class);
    private final MonitoredValueRepository monitoredValueRepository;

    @Autowired
    public MonitoredValueService(MonitoredValueRepository monitoredValueRepository) {
        this.monitoredValueRepository = monitoredValueRepository;
    }

    public List<MonitoredValueDTO> findMonitoredValues() {
        List<MonitoredValue> monitoredValueList = monitoredValueRepository.findAll();
        return monitoredValueList.stream()
                .map(MonitoredValueBuilder::toMonitoredValueDTO)
                .collect(Collectors.toList());
    }

    public MonitoredValueDTO findMonitoredValueById(UUID id) {
        Optional<MonitoredValue> prosumerOptional = monitoredValueRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("MonitoredValue with id {} was not found in db", id);
            throw new ResourceNotFoundException(MonitoredValue.class.getSimpleName() + " with id: " + id);
        }
        return MonitoredValueBuilder.toMonitoredValueDTO(prosumerOptional.get());
    }

    public UUID insert(MonitoredValueDTO monitoredValueDTO) {
        MonitoredValue monitoredValue = MonitoredValueBuilder.toEntity(monitoredValueDTO);
        monitoredValue = monitoredValueRepository.save(monitoredValue);
        LOGGER.debug("MonitoredValue with id {} was inserted in db", monitoredValue.getId());
        return monitoredValue.getId();
    }

}
