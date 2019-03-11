package be.voedsaam.vzw.business;

import be.voedsaam.vzw.business.impl.Partner;

import javax.persistence.*;
import java.util.*;

@Entity
public class Stock extends AbstractDomainClass {
    private String location, name;
    @ElementCollection
    @CollectionTable(name="STOCK_QUANTITY")
    @MapKeyJoinColumn(name="PRODUCT_ID")
    @Column(name="QTY")
    private Map<Product,Integer> products = new HashMap<>();
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "STOCK_PARTNER",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")})
    private List<Partner> users = new ArrayList<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product, Integer quantity){
        product.addStock(this);
        Integer value = products.get(product);
        if (value==null)
            value =0;
        quantity = quantity + value;
        products.put(product,quantity);
    }

    public void removeProduct(Product product, Integer quantity){
        Integer value = products.get(product);
        if (value==null)
            value=0;
        quantity =  value-quantity;
        if (quantity<=0) {
            products.remove(product);
            product.removeStock(this);
        }
        else
            products.put(product,quantity);
    }
    public Map<Product, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }

    public List<Partner> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(Partner user) {
        if (!users.contains(user)) {
            users.add(user);
            user.addStock(this);
        }
    }

    public void removeUser(Partner user) {
        if (users.contains(user)) {
            users.remove(user);
            user.removeStock(this);
        }
    }
    @PreRemove
    public void clear(){
        users.clear();
        products.clear();
    }
}
