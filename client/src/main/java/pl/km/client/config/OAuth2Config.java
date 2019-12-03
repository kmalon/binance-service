package pl.km.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Component;

@Configuration
public class OAuth2Config {

    /**
     * Allegro Api credentials defined in properties file with custom prefix.
     */
    @Data
    @Component
    @ConfigurationProperties(prefix = "allegro")
    public static class AllegroProperties {
        private String clientId;
        private String clientSecret;
        private String tokenName;
        private String accessTokenUri;
    }

    @Bean
    OAuth2RestTemplate oAuth2RestTemplate(AllegroProperties allegroProperties) {
        ClientCredentialsResourceDetails oAuth2ProtectedResourceDetails = new ClientCredentialsResourceDetails();
        oAuth2ProtectedResourceDetails.setAccessTokenUri(allegroProperties.accessTokenUri);
        oAuth2ProtectedResourceDetails.setClientId(allegroProperties.clientId);
        oAuth2ProtectedResourceDetails.setClientSecret(allegroProperties.clientSecret);
        oAuth2ProtectedResourceDetails.setTokenName(allegroProperties.tokenName);
        oAuth2ProtectedResourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails);
    }
}