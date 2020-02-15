package pl.km.binance.api.client;

public class BinanceInvokeError extends RuntimeException {
    BinanceInvokeError(String message) {
        super(message);
    }
}