package pl.km.binance.api.client;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

class TimeProvider {

    private static DateTimeZone DEFAUL_ZONE = DateTimeZone.UTC;

    static long getSystemMilis() {
        return new DateTime(DEFAUL_ZONE).getMillis();
    }
}