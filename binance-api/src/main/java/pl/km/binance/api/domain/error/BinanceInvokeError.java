package pl.km.binance.api.domain.error;

public class BinanceInvokeError extends RuntimeException {
    public BinanceInvokeError(String message) {
        super(message);
    }
}
