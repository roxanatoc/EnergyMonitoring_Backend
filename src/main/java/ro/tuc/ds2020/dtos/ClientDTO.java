package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ClientDTO extends RepresentationModel<ClientDTO> {
    private UUID id;
    private String name;
    private String address;
    private Date birthdate;


    public ClientDTO() {

    }

    public ClientDTO(UUID id, String name, String address, Date birthdate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public UUID getId() {
        return id;
        }

    public void setId(UUID id) {
        this.id = id;
        }

    public String getName() {
        return name;
        }

    public void setName(String name) {
        this.name = name;
        }

    public String getAddress() {
        return address;
        }

    public void setAddress(String address) {
        this.address = address;
        }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return address == clientDTO.address &&
        Objects.equals(name, clientDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, birthdate);
        }
}
