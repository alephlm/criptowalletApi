package com.criptowallet.criptowallet.controllers;

import com.criptowallet.criptowallet.Services.PortfolioItemService;
import com.criptowallet.criptowallet.entities.PortfolioItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
@RestController
public class PortfolioItemController {

    private final PortfolioItemService portfolioItemService;

    public PortfolioItemController(PortfolioItemService portfolioItemService) {
        this.portfolioItemService = portfolioItemService;
    }

    @RequestMapping("/all")
    public List<PortfolioItem> GetAll() {
        return portfolioItemService.getAllPortfolioItems();
    }


    @GetMapping("/get")
    public PortfolioItem SaveItem(Long id) {
        return portfolioItemService.getPortfolioItemById(id);
    }

    @PostMapping("/save")
    public void SaveItem(@RequestBody PortfolioItem portfolioItem) {
        portfolioItemService.savePortfolioItem(portfolioItem);
    }

    @DeleteMapping("/delete")
    public void DeleteItem(Long id) {
        portfolioItemService.deletePortfolioItemById(id);
    }
}
