package de.helfenkannjeder.typo3userstokeycloakmigration.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class KeycloakRestApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final String keycloakBaseUri;
    private final String keycloakRealm;

    @Autowired
    public KeycloakRestApi(RestTemplate restTemplate,
                           @Value("${keycloak.baseUri}") String keycloakBaseUri,
                           @Value("${keycloak.realm}") String keycloakRealm) {
        this.restTemplate = restTemplate;
        this.keycloakBaseUri = keycloakBaseUri;
        this.keycloakRealm = keycloakRealm;
    }

    public void createUser(KeycloakUserToCreate user) {
        try {
            restTemplate.exchange(keycloakBaseUri + "/auth/admin/realms/" + keycloakRealm + "/users",
                    HttpMethod.POST,
                    new HttpEntity<>(user),
                    String.class);

            logger.info("User {} was imported", user.getEmail());
        }
        catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.CONFLICT) {
                logger.warn("User {} already exists", user.getEmail());
            }
            else {
                logger.error("Create user failed for {} with code {} and response {}",
                        user.getEmail(),
                        ex.getStatusCode(),
                        ex.getResponseBodyAsString());
            }
        }
    }
}
