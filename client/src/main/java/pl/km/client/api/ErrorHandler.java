package pl.km.client.api;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.km.binance.api.client.BinanceInvokeError;

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(BinanceInvokeError.class)
    ResponseEntity<ErrorResponse> handleBinanceInvokeError(BinanceInvokeError error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(error.getMessage()));
    }

    @ExceptionHandler(HystrixTimeoutException.class)
    ResponseEntity<ErrorResponse> handleHystrixTimeoutException(HystrixTimeoutException error) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(new ErrorResponse(error.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    ResponseEntity<ErrorResponse> handleFeignException(FeignException error) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse(error.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorResponse> handleGeneralExceptions(RuntimeException error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(error.getMessage()));
    }
}