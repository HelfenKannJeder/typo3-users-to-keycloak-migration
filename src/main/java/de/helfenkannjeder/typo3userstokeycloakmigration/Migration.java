package de.helfenkannjeder.typo3userstokeycloakmigration;

import de.helfenkannjeder.typo3userstokeycloakmigration.keycloak.KeycloakRestApi;
import de.helfenkannjeder.typo3userstokeycloakmigration.keycloak.KeycloakUserToCreate;
import de.helfenkannjeder.typo3userstokeycloakmigration.typo3.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Migration {
    private final UserRepository userRepository;
    private final KeycloakRestApi keycloakRestApi;
    private final String emailFilter;

    @Autowired
    public Migration(UserRepository userRepository,
                     KeycloakRestApi keycloakRestApi,
                     @Value("${import.emailFilter}") String emailFilter
    ) {
        this.userRepository = userRepository;
        this.keycloakRestApi = keycloakRestApi;
        this.emailFilter = emailFilter;
    }

    public void doMigration() {
        userRepository.findAllActiveUsers().stream()
                .filter(typo3User -> !typo3User.getEmail().matches(emailFilter)
                                    && typo3User.getEmail()!=null && !typo3User.getEmail().isEmpty() && !typo3User.getEmail().isBlank()
                                    && !typo3User.isDeleted() && !typo3User.isDisable()
                )
                .map(KeycloakUserToCreate::from)
                .forEach(keycloakRestApi::createUser);
    }
}
