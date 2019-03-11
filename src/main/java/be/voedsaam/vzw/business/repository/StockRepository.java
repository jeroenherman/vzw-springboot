package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
