package pl.km.binance.api.client;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
class BinanceServerErrorResponse {
    private String code;
    private String msg;

    String getErrorMessage() {
        return "Code (" + code + "): " + msg;
    }
}