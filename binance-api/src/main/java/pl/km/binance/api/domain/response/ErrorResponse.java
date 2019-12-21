package pl.km.binance.api.domain.response;

import lombok.Value;

@Value
public class ErrorResponse {
    private String code;
    private String message;
}