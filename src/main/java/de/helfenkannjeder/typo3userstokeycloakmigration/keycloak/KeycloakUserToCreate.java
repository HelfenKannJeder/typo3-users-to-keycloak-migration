package de.helfenkannjeder.typo3userstokeycloakmigration.keycloak;

import com.google.common.collect.Lists;
import de.helfenkannjeder.typo3userstokeycloakmigration.typo3.Typo3User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeycloakUserToCreate {
    String email;
    String firstName;
    String lastName;
    String username;
    List<Credential> credentials;
    Boolean enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public static KeycloakUserToCreate from(Typo3User typo3User) {
        KeycloakUserToCreate keycloakUserToCreate = new KeycloakUserToCreate();
        keycloakUserToCreate.setUsername(typo3User.getUsername());
        keycloakUserToCreate.setFirstName(typo3User.getFirstName());
        keycloakUserToCreate.setLastName(typo3User.getLastName());
        keycloakUserToCreate.setEmail(typo3User.getEmail());
        keycloakUserToCreate.setEnabled(true);

        Pattern passwordPattern = Pattern.compile("(\\$1\\$.+)\\$(.+)");
        Matcher passwordGroups = passwordPattern.matcher(typo3User.getPassword());
        if(passwordGroups.find()) {
            keycloakUserToCreate.setCredentials(Lists.newArrayList(new Credential("php-crypt-md5", passwordGroups.group(2), passwordGroups.group(1), -1)));
        }
        else
        {
            throw new RuntimeException("Can not read password hash for user " + typo3User.getEmail());
        }

        return keycloakUserToCreate;
    }
}
