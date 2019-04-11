package com.criptowallet.criptowallet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private CryptocurrencyTypeEnum type;
    private Double amount;
    private LocalDateTime dateOfPurchase;
    private String walletLocation;
    private Double currentMarketValue; //Current market value (EUR)

    public PortfolioItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CryptocurrencyTypeEnum getType() {
        return type;
    }

    public void setType(CryptocurrencyTypeEnum cryptocurrency) {
        this.type = cryptocurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getWalletLocation() {
        return walletLocation;
    }

    public void setWalletLocation(String walletLocation) {
        this.walletLocation = walletLocation;
    }

    public Double getCurrentMarketValue() {
        return currentMarketValue;
    }

    public void setCurrentMarketValue(Double currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }
}
