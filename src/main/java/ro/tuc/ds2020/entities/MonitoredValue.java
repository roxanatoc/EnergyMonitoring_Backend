package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class MonitoredValue implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "sensor_id", nullable = false)
    private UUID sensor_id;

    /*@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_sensor", referencedColumnName = "id")
    private Sensor sensor;
     */

    public MonitoredValue() {
    }

    public MonitoredValue(Timestamp timestamp, float value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public MonitoredValue(UUID id, Timestamp timestamp, float value, UUID sensor_id) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
        this.sensor_id = sensor_id;
    }

    public MonitoredValue(Timestamp timestamp, float value, UUID sensor_id) {
        this.timestamp = timestamp;
        this.value = value;
        this.sensor_id = sensor_id;
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
}
