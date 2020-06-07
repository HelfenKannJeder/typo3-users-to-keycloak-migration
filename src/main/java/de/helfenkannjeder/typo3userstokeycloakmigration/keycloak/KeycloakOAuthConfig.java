package de.helfenkannjeder.typo3userstokeycloakmigration.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class KeycloakOAuthConfig {

    @Value("${keycloak.clientId}")
    private String clientId;

    @Value("${keycloak.clientSecret}")
    private String clientSecret;

    @Value("${keycloak.tokenUri}")
    private String tokenUri;

    @Bean
    public OAuth2ProtectedResourceDetails keycloakClientCredentialsResourceDetails() {

        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(tokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType("client_credentials");

        return  resourceDetails;
    }

    @Bean
    public OAuth2RestTemplate keycloakRestTemplate(OAuth2ProtectedResourceDetails keycloakClientCredentialsResourceDetails) {
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(keycloakClientCredentialsResourceDetails);
        oAuth2RestTemplate.getMessageConverters().add(new ResourceHttpMessageConverter());

        return oAuth2RestTemplate;
    }
}
