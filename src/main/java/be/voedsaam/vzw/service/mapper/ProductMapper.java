package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.business.repository.ProductRepository;
import be.voedsaam.vzw.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ProductMapper {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductDTO mapToDTO(Product b) {
        if (b == null)
            return null;
        ProductDTO d = new ProductDTO();
        d.setId(b.getId());
        d.setDeliveryNr(b.getDeliveryNr());
        d.setDescription(b.getDescription());
        d.setName(b.getName());
        d.setProductType(b.getProductType());
        if (b.getSchelfLife() != null)
            d.setShelfLife(b.getSchelfLife().format(DateTimeFormatter.ISO_DATE));
        d.setUnitOfMeasure(b.getUnitOfMeasure());
        d.setProductType(b.getProductType());
        if (b.getStocks().size() > 1)
            d.setInStock(true);
        else d.setInStock(false);
        return d;
    }


    public Product mapToObj(ProductDTO d) {
        if (d == null)
            return null;
        Product b = new Product();
        Optional<Product> o = Optional.empty();
        if (d.getId() != null)
            o = productRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();
        if (d.getName()!=null&&d.getProductType()!=null&&d.getUnitOfMeasure()!=null) {
            b.setId(d.getId());
            b.setDeliveryNr(d.getDeliveryNr());
            b.setDescription(d.getDescription());
            b.setName(d.getName());
            b.setProductType(d.getProductType());
            if (d.getShelfLife().length() > 1)
                b.setSchelfLife(LocalDate.parse(d.getShelfLife(), DateTimeFormatter.ISO_DATE));
            b.setUnitOfMeasure(d.getUnitOfMeasure());
            b.setProductType(d.getProductType());
        }

        return b;
    }

    public List<ProductDTO> mapToDTO(Map<Product,Integer> products) {
        List<ProductDTO> dList = new ArrayList<>();
        for (Product p: products.keySet()
             ) {
            ProductDTO d =  mapToDTO(p);
            d.setQty(products.get(p));
            dList.add(d);
        }
        return dList;
    }

    public Map<Product, Integer> mapToObj(List<ProductDTO> dList){
        Map<Product, Integer> products = new HashMap<>();
        for (ProductDTO d: dList
             ) {
            Product b = mapToObj(d);
            if(d.getQty()!=null)
            products.put(b,d.getQty());
        }
        return products;
    }

    public List<ProductDTO> mapToDTO(List<Product> products){
        List<ProductDTO> dList = new ArrayList<>();
        for (Product p: products
             ) {
            ProductDTO d = mapToDTO(p);
            dList.add(d);
        }
        return dList;
    }

}
