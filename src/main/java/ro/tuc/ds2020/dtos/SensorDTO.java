package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Device;

import java.util.Objects;
import java.util.UUID;

public class SensorDTO extends RepresentationModel<SensorDTO> {
    private UUID id;
    private String description;
    private float maxValue;
    private Device device;

    public SensorDTO() {
    }

    public SensorDTO(UUID id, String description, float maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

    public SensorDTO(UUID id, String description, float maxValue, Device device) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDTO sensorDTO = (SensorDTO) o;
        return description == sensorDTO.description &&
        Objects.equals(description, sensorDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, maxValue);
    }
}
