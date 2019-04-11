package com.criptowallet.criptowallet.Repositories;

import com.criptowallet.criptowallet.entities.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
}