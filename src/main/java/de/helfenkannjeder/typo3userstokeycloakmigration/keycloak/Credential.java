package de.helfenkannjeder.typo3userstokeycloakmigration.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

public class Credential {
    String type;
    String secretData;
    String credentialData;

    public Credential(String algorithm, String hashedValue, String salt, int hashIterations) {
        try {
            this.secretData = new ObjectMapper().writeValueAsString(new SecretData(hashedValue, salt));
            this.credentialData = new ObjectMapper().writeValueAsString(new CredentialData(hashIterations, algorithm));
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.type = "password";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecretData() {
        return secretData;
    }

    public void setSecretData(String secretData) {
        this.secretData = secretData;
    }

    public String getCredentialData() {
        return credentialData;
    }

    public void setCredentialData(String credentialData) {
        this.credentialData = credentialData;
    }

    static class SecretData {
        public String value;
        public String salt;

        public SecretData(String value, String salt) {
            this.value = value;
            this.salt = Base64.encodeBase64String(salt.getBytes());
        }
    }

    static class CredentialData {
        public Integer hashIterations;
        public String algorithm;

        public CredentialData(Integer hashIterations, String algorithm) {
            this.hashIterations = hashIterations;
            this.algorithm = algorithm;
        }
    }
}
