package pl.km.binance.api.domain.error;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String msg;

    String getErrorMessage() {
        return "Code (" + code + "): " + msg;
    }
}