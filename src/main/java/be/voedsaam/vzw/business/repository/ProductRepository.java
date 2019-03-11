package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
