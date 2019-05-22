package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.repository.StockRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class StockServiceRepoImpl implements StockService {
    private StockRepository stockRepository;
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<?> listAll() {
        List<Stock> contacts = new ArrayList<>();
        stockRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    @Override
    public Stock getById(Long id) {
        Optional<Stock> o = stockRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }

    @Override
    public Stock saveOrUpdate(Stock domainObject) {
        return stockRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Stock> o = stockRepository.findById(id);
            if (o.isPresent())
                stockRepository.delete(o.get());
        }
    }

    @Override
    public List<Stock> listAllByUser(String name) {
        return stockRepository.findAllByUsersContaining(userRepository.findByEmailIgnoreCase(name));
    }
}
