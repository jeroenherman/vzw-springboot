package be.voedsaam.vzw.business;

import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Entity
// table name must be different from order because order is a restricted verb in sql
@Table(name = "ORDER_PRT")
public class Order extends AbstractDomainClass {
    private LocalDateTime pickupDateTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ElementCollection
    @CollectionTable(name="ORDER_QUANTITY")
    @MapKeyJoinColumn(name="PRODUCT_ID")
    @Column(name="QTY")
    private Map<Product,Integer> products = new HashMap<>();
    @ManyToOne
    private Partner partner;
    public void addProduct(Product product, Integer quantity){
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
        }
        else
            products.put(product,quantity);
    }
    public Map<Product, Integer> getProducts(){
        return Collections.unmodifiableMap(products);
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

}
