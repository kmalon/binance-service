package pl.km.client.binance.domain.response;

import lombok.Value;

@Value
public class ErrorResponse {
    private String code;
    private String message;
}