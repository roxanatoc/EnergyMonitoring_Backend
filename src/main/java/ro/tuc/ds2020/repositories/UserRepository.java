package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //List<User> findByUsername(String username);

    @Query(value = "SELECT u " +
            "FROM User u " +
            "WHERE u.username = :username ")
    Optional<User> findByUsername(@Param("username") String username);
}
