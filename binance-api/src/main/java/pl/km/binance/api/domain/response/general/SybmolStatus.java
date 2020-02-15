package pl.km.binance.api.domain.response.general;

/**
 * Stymbol status in exchange
 */
public enum SybmolStatus {
    PRE_TRADING,
    TRADING,
    POST_TRADING,
    END_OF_DAY,
    HALT,
    AUCTION_MATCH,
    BREAK;
}