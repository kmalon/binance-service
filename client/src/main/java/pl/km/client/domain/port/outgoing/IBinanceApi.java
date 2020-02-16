package pl.km.client.domain.port.outgoing;

import pl.km.client.domain.common.ExchangeInfo;

public interface IBinanceApi {

    ExchangeInfo getExchangeInfo();
}