package be.voedsaam.vzw.business.impl;
import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Partner extends User {
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "users")
    private List<Stock> stocks = new ArrayList<>();
    @OneToMany(mappedBy = "partner", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

    public Partner() {
        setRole(Role.PARTNER);

    }

    public Partner(String fullName) {
        super(fullName);
    }

    public Partner(String firstName, String lastName, String email, String tel) {
        super(firstName, lastName, email, tel);
    }

    @Override
    public void setRole(Role role) {
        super.setRole(Role.PARTNER);
    }

    public void addStock(Stock stock) {
        if (!stocks.contains(stock)) {
            stocks.add(stock);
            stock.addUser(this);
        }
    }

    public void removeStock(Stock stock) {
        if (stocks.contains(stock)) {
            stock.removeUser(this);
            stocks.remove(stock);
        }
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }

    public void addOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
            order.setPartner(this);
        }
    }
    public void removeOrder(Order order){
        if (orders.contains(order)) {
            orders.remove(order);
            order.setPartner(null);
        }
    }


    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
