package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "maxValue", nullable = false)
    private float maxValue;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_device", referencedColumnName = "id")
    private Device device;

    /*@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<MonitoredValue> values;

     */


    public Sensor() {
    }

    public Sensor(String description, float maxValue) {
        this.description = description;
        this.maxValue = maxValue;
    }

    public Sensor(UUID id, String description, float maxValue, Device device) {
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
}
