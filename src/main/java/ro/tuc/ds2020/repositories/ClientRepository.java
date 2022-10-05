package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByName(String name);

    @Query(value = "SELECT c " +
        "FROM Client c " +
        "WHERE c.name = :name " +
        "AND c.address = 'Cluj-Napoca'")
    Optional<Client> findSeniorsByName(@Param("name") String name);
}
