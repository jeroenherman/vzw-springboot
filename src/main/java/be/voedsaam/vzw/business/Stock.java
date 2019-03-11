package be.voedsaam.vzw.business;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Stock extends AbstractDomainClass {
    private String location;
    @ElementCollection
    @CollectionTable(name="PRODUCT_QUANTITY")
    @MapKeyJoinColumn(name="PRODUCT_ID")
    @Column(name="QTY")
    private HashMap<Product,Integer> products = new HashMap<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addProduct(Product product, Integer quantity){
        product.addStock(this);
        Integer value = products.get(product);
        value = value + quantity;
        products.put(product,value);
    }

    public void removeProduct(Product product, Integer quantity){
        Integer value = products.get(product);
        value = value-quantity;
        if (value<0) {
            products.remove(product);
            product.removeStock(this);
        }
    }
    public Map<Product, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }
}
