package pl.km.binance.api.client;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpStatus;

@Getter
class ErrorExceptionResponse extends RuntimeException {
    @JsonSerialize(using = JodaDateTimeSerializer.class)
    private DateTime timestamp;
    private HttpStatus status;
    private String error;

    ErrorExceptionResponse(HttpStatus status, String error) {
        timestamp = new DateTime(DateTimeZone.UTC);
        this.status = status;
        this.error = error;
    }
}