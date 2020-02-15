package pl.km.binance.api.domain.response.general;

import lombok.Data;

@Data
public class RateLimit {
    private RateLimitType rateLimitType;
    private Interval interval;
    private int intervalNum;
    private int limit;
}
