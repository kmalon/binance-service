package pl.km.binance.api.client;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * REST API requests mappings
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class BinanceApiRestMappings {
//              PUBLIC
    /**
     * Ping endpoint for testing connectivity
     */
    static final String PING = "/ping";

    /**
     * Server time
     */
    static final String SERVER_TIME = "/time";

    /**
     * Current exchange trading rules and symbol information
     */
    static final String EXCHANGE_INFO = "/exchangeInfo";


//              ACCOUNT ENDPOINTS
    /**
     * Get current account information
     */
    static final String ACCOUNT_INFORMATIONS = "/account";

    /**
     * Get trades for a specific account and symbol.
     */
    static final String MY_TRADES = "/myTrades";

    /**
     * Retrieves all OCO based on provided optional parameters
     */
    static final String ALL_ORDERS_LIST = "/allOrderList";

    /**
     * Retrieves a specific OCO based on provided optional parameters
     */
    static final String QUERY_OCO = "/orderList";
}