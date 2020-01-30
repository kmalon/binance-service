package pl.km.binance.api.domain.error.binance.server;

public class BinanceInvokeError extends RuntimeException {
    public BinanceInvokeError(String message) {
        super(message);
    }
}