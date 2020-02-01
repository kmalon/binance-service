package pl.km.binance.api.infrastructure.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.km.binance.api.domain.error.api.ErrorExceptionResponse;
import pl.km.binance.api.domain.error.binance.server.BinanceInvokeError;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.infrastructure.IBinanceApClientRest;

import java.util.List;
import java.util.Map;

//todo either?
public class HystrixBinanceFallbackFactory implements FallbackFactory<IBinanceApClientRest> {
    @Override
    public IBinanceApClientRest create(Throwable cause) {
        return new IBinanceApClientRest() {
            @Override
            public ResponseEntity<Object> ping() {
                decomposeException(cause);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }

            @Override
            public ResponseEntity<ServerTime> serverTime() {
                decomposeException(cause);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }

            @Override
            public ResponseEntity<ExchangeInfo> exchangeInfo() {
                decomposeException(cause);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }

            @Override
            public ResponseEntity<AccountInfo> getAccountInfo(Map<String, ?> params, String apiKey) {
                decomposeException(cause);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }

            @Override
            public ResponseEntity<List<Trades>> getUserTrades(Map<String, ?> params, String apiKey) {
                decomposeException(cause);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }
        };
    }

    private static ResponseEntity decomposeException(Throwable cause) {
        if (cause instanceof BinanceInvokeError) {
            throw new ErrorExceptionResponse(HttpStatus.BAD_REQUEST, cause.getMessage());
        } else if (cause instanceof HystrixTimeoutException) {
            throw new ErrorExceptionResponse(HttpStatus.REQUEST_TIMEOUT, cause.getMessage());
        } else if (cause instanceof FeignException) {
            throw new ErrorExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, cause.getMessage());
        }
        throw new ErrorExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
    }
}