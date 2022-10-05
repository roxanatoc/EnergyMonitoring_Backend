package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public DeviceService(DeviceRepository deviceRepository, ClientRepository clientRepository) {
        this.deviceRepository = deviceRepository;
        this.clientRepository = clientRepository;
    }

    public List<DeviceDTO> findDevice() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public List<DeviceDTO> findDevicesForClient(UUID clientID) {
        List<Client> clientList = clientRepository.findAll();
        List<Device> devices;
        for (Client c : clientList) {
            if (c.getId().equals(clientID)) {
                devices = c.getDevices();
                return devices.stream()
                        .map(DeviceBuilder::toDeviceDTO)
                        .collect(Collectors.toList());

            }
        }
        return null;
    }


    public DeviceDTO findDeviceById(UUID id) {
        Optional<Device> prosumerOptional = deviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDTO(prosumerOptional.get());
    }

    public UUID insert(DeviceDTO deviceDTO) {
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public DeviceDTO update(DeviceDTO deviceDTO, UUID deviceID) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceID);
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", deviceID);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceID);
        }
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device.setAddress(deviceDTO.getAddress());
        device.setDescription(deviceDTO.getDescription());
        device.setMaxEnergy(deviceDTO.getMaxEnergy());
        device.setAverageEnergy(deviceDTO.getAverageEnergy());
        deviceRepository.save(device);
        return deviceDTO;
    }

    public void delete(UUID id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        deviceRepository.deleteById(id);
        LOGGER.debug("Device with id {} was deleted from the db", id);
    }

    /*public void assocClient(UUID deviceID, UUID clientID) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceID);
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", deviceID);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceID);
        }
        Optional<Client> clientOptional = clientRepository.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", clientID);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + clientID);
        }
        Device device = deviceOptional.get();
        device.setId_client(clientID);
        deviceRepository.save(device);

    }

     */

}
