package pl.km.client.binance.domain.exchange.general;

import lombok.Data;

@Data
public class RateLimit {
    private RateLimitType rateLimitType;
    private Interval interval;
    private int intervalNum;
    private int limit;
}
