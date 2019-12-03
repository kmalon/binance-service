package pl.km.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.Assert;

@Configuration
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    /**
     * The logger instance used by this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    /**
     * The authorization header name.
     */
    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * The {@code Bearer} token type.
     */
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    /**
     * The version header name;
     */
    private static final String API_VERSION_HEADER = "Accept";

    /**
     * Api version of requested endpoint
     */
    private static final String API_VERSION = "application/vnd.allegro.public.v1+json";

    /**
     * Current OAuth2 authentication client.
     */
    private final OAuth2RestTemplate oAuth2RestTemplate;

    /**
     * Creates new instance of {@link OAuth2FeignRequestInterceptor} with client oAuth2 client.
     *
     * @param oAuth2RestTemplate the OAuth2 client
     */
    public OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        Assert.notNull(oAuth2RestTemplate, "OAuth2Client can not be null");
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    /**
     * Adding Authorization header {@link AUTHORIZATION_HEADER} to request if is valid or getting new one
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (requestTemplate.headers().containsKey(AUTHORIZATION_HEADER)) {
            LOGGER.warn("The Authorization token has been already set");
        } else if (oAuth2RestTemplate.getAccessToken() == null) {
            LOGGER.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            LOGGER.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
            setAuthorizationsHeader(requestTemplate);
            setApiVersion(requestTemplate);
        }
    }

    private void setAuthorizationsHeader(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                oAuth2RestTemplate.getAccessToken().toString()));
    }

    private void setApiVersion(RequestTemplate requestTemplate) {
        requestTemplate.header(API_VERSION_HEADER, API_VERSION);
    }
}