package pl.km.binance.api.domain.error.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpStatus;
import pl.km.binance.api.domain.time.JodaDateTimeSerializer;

@Getter
public class ErrorExceptionResponse extends RuntimeException {
    @JsonSerialize(using = JodaDateTimeSerializer.class)
    private DateTime timestamp;
    private HttpStatus status;
    private String error;

    public ErrorExceptionResponse(HttpStatus status, String error) {
        timestamp = new DateTime(DateTimeZone.UTC);
        this.status = status;
        this.error = error;
    }
}