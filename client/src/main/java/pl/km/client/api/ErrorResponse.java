package pl.km.client.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Getter
class ErrorResponse {
    @JsonSerialize(using = JodaDateTimeSerializer.class)
    private DateTime timestamp;
    private String error;

    ErrorResponse(String error) {
        timestamp = new DateTime(DateTimeZone.UTC);
        this.error = error;
    }
}