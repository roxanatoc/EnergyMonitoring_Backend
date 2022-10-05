package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.MonitoredValue;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface MonitoredValueRepository extends JpaRepository<MonitoredValue, UUID> {

    //List<MonitoredValue> findByTimestamp(Date timestamp);

    @Query(value = "SELECT m " +
            "FROM MonitoredValue m " +
            "WHERE m.timestamp = :timestamp " +
            "AND m.value = 10.0 ")
    Optional<MonitoredValue> findSeniorsByTimestamp(@Param("timestamp") Date timestamp);
}
