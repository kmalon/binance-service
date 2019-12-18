package pl.km.client.binance.domain.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * REST API requests mappings
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BinanceApiRestMappings {
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
     * Get trades for a specific account and symbol.
     */
    public static final String MY_TRADES = "/myTrades";

    /**
     * Retrieves all OCO based on provided optional parameters
     */
    public static final String ALL_ORDERS_LIST = "/allOrderList";
}