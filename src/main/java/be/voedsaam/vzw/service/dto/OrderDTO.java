package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private String pickupDateTime, partner, stock;
    private OrderStatus orderStatus;
    private LocalDateTime pickUp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getPickUp() {
        return pickUp;
    }

    public void setPickUp(LocalDateTime pickUp) {
        this.pickUp = pickUp;
    }
}


