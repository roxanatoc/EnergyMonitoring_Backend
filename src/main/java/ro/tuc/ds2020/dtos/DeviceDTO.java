package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Client;

import java.util.Objects;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    private UUID id;
    private String description;
    private String address;
    private String maxEnergy;
    private String averageEnergy;
    private Client client;

    public DeviceDTO() {
    }

    public DeviceDTO(UUID id, String description, String address, String maxEnergy, String averageEnergy) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergy = maxEnergy;
        this.averageEnergy = averageEnergy;
    }
    public DeviceDTO(UUID id, String description, String address, String maxEnergy, String averageEnergy, Client client) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergy = maxEnergy;
        this.averageEnergy = averageEnergy;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(String maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public String getAverageEnergy() {
        return averageEnergy;
    }

    public void setAverageEnergy(String averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return address == deviceDTO.address &&
        Objects.equals(address, deviceDTO.address);
        }


    @Override
    public int hashCode() {
        return Objects.hash(description, address, maxEnergy, averageEnergy);
    }
}
