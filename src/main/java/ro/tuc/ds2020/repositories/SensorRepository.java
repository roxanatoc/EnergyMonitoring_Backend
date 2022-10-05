package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Sensor;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {

    //List<Sensor> findByDescription(String description);

    @Query(value = "SELECT s " +
            "FROM Sensor s " +
            "WHERE s.description = :description " +
            "AND s.maxValue = '100 kWh' ")
    Optional<Sensor> findSeniorsByDescription(@Param("description") String description);
}
