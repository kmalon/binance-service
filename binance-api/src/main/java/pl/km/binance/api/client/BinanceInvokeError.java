package pl.km.binance.api.client;

class BinanceInvokeError extends RuntimeException {
    BinanceInvokeError(String message) {
        super(message);
    }
}