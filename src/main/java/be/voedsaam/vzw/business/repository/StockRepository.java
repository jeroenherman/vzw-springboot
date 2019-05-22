package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAllByUsersContaining(User user);
}
