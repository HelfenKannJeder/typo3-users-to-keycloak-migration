package de.helfenkannjeder.typo3userstokeycloakmigration.typo3;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<Typo3User, Integer> {

    @Query("SELECT u FROM Typo3User u")
    Collection<Typo3User> findAllActiveUsers();
}
