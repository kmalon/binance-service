package pl.km.client.domain.port.incoming;

import pl.km.client.domain.common.ExchangeInfo;

public interface IExchangeFacade {
    ExchangeInfo getExchangeInfo();

    void synchronizeExchangeInfo();
}