package com.criptowallet.criptowallet.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.criptowallet.criptowallet.Repositories.PortfolioItemRepository;
import com.criptowallet.criptowallet.entities.CriptoCurrency;
import com.criptowallet.criptowallet.entities.CryptocurrencyTypeEnum;
import com.criptowallet.criptowallet.entities.PortfolioItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class PortfolioItemServiceImpl implements PortfolioItemService {

    public final PortfolioItemRepository repository;

    public PortfolioItemServiceImpl(PortfolioItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public PortfolioItem savePortfolioItem(PortfolioItem item) {
        return repository.save(item);
    }

    @Override
    public List<PortfolioItem> getAllPortfolioItems() {
        List<PortfolioItem> portfolioItems = repository.findAll();
        portfolioItems.forEach(item -> {
            try {
                item.setCurrentMarketValue(item.getAmount() * getLastValueForCurrency(item.getType()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return portfolioItems;
    }

    @Override
    public PortfolioItem getPortfolioItemById(long id) {
        try{
            Double lastValue = APIRequest.requestCriptoCurrencyLastValue("https://api.bitfinex.com/v1/pubticker/btceur");
            PortfolioItem portfolioItem = repository.findById(id).get();
            portfolioItem.setCurrentMarketValue(portfolioItem.getAmount() * lastValue);
            return portfolioItem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePortfolioItemById(long id) {
        repository.deleteById(id);
    }

    private Double getLastValueForCurrency(CryptocurrencyTypeEnum type) throws IOException {
        Double currencyValue = 0.0;
        if(type == CryptocurrencyTypeEnum.bitcoin) {
            currencyValue = APIRequest.requestCriptoCurrencyLastValue("https://api.bitfinex.com/v1/pubticker/btceur");
        } else if (type == CryptocurrencyTypeEnum.ethereum) {
            currencyValue = APIRequest.requestCriptoCurrencyLastValue("https://api.bitfinex.com/v1/pubticker/etheur");
        } else if (type == CryptocurrencyTypeEnum.ripple) {
            currencyValue = APIRequest.requestCriptoCurrencyLastValue("https://api.bitfinex.com/v1/pubticker/xrpusd");
            currencyValue = convertToEuro(currencyValue);
        }
        return currencyValue;
    }

    private Double convertToEuro(Double usd) throws IOException {
        Double eurValueInUSD = APIRequest.requestValueUSD("https://api.exchangeratesapi.io/latest?symbols=USD");
        return usd / eurValueInUSD;
    }

}
