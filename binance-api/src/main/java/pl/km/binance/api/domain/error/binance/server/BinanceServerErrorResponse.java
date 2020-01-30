package pl.km.binance.api.domain.error.binance.server;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class BinanceServerErrorResponse {
    private String code;
    private String msg;

    String getErrorMessage() {
        return "Code (" + code + "): " + msg;
    }
}