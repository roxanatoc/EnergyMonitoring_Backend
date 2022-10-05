package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.UUID;


public class SensorValue {

    private UUID sensor_id;
    private Timestamp timestamp;
    private float measurement_value;

    public SensorValue(@JsonProperty("sensor_id") final UUID sensor_id,
                       @JsonProperty("timestamp") final Timestamp timestamp,
                       @JsonProperty("measurement_value") final float measurement_value) {
        this.sensor_id = sensor_id;
        this.timestamp = timestamp;
        this.measurement_value = measurement_value;
    }

    public SensorValue() {
    }

    public UUID getSensor_id() {
        return sensor_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public float getMeasurement_value() {
        return measurement_value;
    }

    public void setSensor_id(UUID sensor_id) {
        this.sensor_id = sensor_id;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setMeasurement_value(float measurement_value) {
        this.measurement_value = measurement_value;
    }

    @Override
    public String toString() {
        return "SensorValue{" +
                "sensor_id=" + sensor_id +
                ", timestamp=" + timestamp +
                ", measurement_value=" + measurement_value +
                '}';
    }
}
