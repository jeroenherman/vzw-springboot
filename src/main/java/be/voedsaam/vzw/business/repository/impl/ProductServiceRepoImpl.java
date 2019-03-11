package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.business.repository.ProductRepository;
import be.voedsaam.vzw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<?> listAll() {
        List<Product> contacts = new ArrayList<>();
        productRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> o = productRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Product> o = productRepository.findById(id);
            if (o.isPresent())
                productRepository.delete(o.get());
        }
    }
}
