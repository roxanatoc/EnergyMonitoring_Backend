package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class MonitoredValueDTO extends RepresentationModel<MonitoredValueDTO> {
    private UUID id;
    private Timestamp timestamp;
    private float value;
    private UUID sensorId;

    public MonitoredValueDTO() {
    }

    public MonitoredValueDTO(UUID id, Timestamp timestamp, float value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


    @Override
public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoredValueDTO monitoredValueDTO = (MonitoredValueDTO) o;
        return timestamp == monitoredValueDTO.timestamp &&
        Objects.equals(timestamp, monitoredValueDTO.timestamp);
        }


    @Override
    public int hashCode() {
        return Objects.hash(timestamp, value);
    }
}
