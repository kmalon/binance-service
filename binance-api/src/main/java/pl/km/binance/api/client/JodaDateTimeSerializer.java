package pl.km.binance.api.client;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

class JodaDateTimeSerializer extends JsonSerializer<DateTime> {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN).withZone(DateTimeZone.UTC);
        gen.writeString(formatter.print(value));
    }
}