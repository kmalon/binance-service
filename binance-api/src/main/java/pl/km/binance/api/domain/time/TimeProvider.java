package pl.km.binance.api.domain.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimeProvider {

    private static DateTimeZone DEFAUL_ZONE = DateTimeZone.UTC;

    static long getSystemMilis() {
        return new DateTime(DEFAUL_ZONE).getMillis();
    }
}