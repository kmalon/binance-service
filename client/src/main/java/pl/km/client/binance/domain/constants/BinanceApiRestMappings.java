package pl.km.client.binance.domain.constants;

/**
 * REST API requests mappings
 */
public class BinanceApiRestMappings {
//              PUBLIC
    /**
     * Ping endpoint for testing connectivity
     */
    public static final String PING = "/ping";

    /**
     * Server time
     */
    public static final String SERVER_TIME = "/time";

    /**
     * Current exchange trading rules and symbol information
     */
    public static final String EXCHANGE_INFO = "/exchangeInfo";


//              ACCOUNT ENDPOINTS
    /**
     * Get current account information
     */
    public static final String ACCOUNT_INFORMATIONS = "/account";

    /**
     * Retrieves all OCO based on provided optional parameters
     */
    public static final String ALL_ORDERS_LIST = "/allOrderList";
}