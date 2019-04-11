package com.criptowallet.criptowallet.Services;

import com.criptowallet.criptowallet.entities.PortfolioItem;

import java.util.List;

public interface PortfolioItemService {

    PortfolioItem savePortfolioItem(PortfolioItem item);

    List<PortfolioItem> getAllPortfolioItems();

    PortfolioItem getPortfolioItemById(long id);

    void deletePortfolioItemById(long id);
}
