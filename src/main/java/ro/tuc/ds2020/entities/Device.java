package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Device implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "maxEnergy", nullable = false)
    private String maxEnergy;

    @Column(name = "averageEnergy", nullable = false)
    private String averageEnergy;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    @OneToOne(mappedBy = "device")
    private Sensor sensor;


    public Device() {
    }

    public Device(String description, String address, String maxEnergy, String averageEnergy) {
        this.description = description;
        this.address = address;
        this.maxEnergy = maxEnergy;
        this.averageEnergy = averageEnergy;
    }

    public Device(UUID id, String description, String address, String maxEnergy, String averageEnergy, Client client) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergy = maxEnergy;
        this.averageEnergy = averageEnergy;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Client getClient() {
        return client;
    }

    public Sensor getSensor() {
        return sensor;
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

}
