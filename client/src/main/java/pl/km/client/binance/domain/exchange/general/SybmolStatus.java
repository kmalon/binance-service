package pl.km.client.binance.domain.exchange.general;

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