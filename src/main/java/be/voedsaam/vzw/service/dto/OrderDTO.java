package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private String pickupDateTime, partner, stock, comment;
    private OrderStatus orderStatus;
    private LocalDateTime pickUpDateTime;
    private boolean pickup;

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

    public LocalDateTime getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(LocalDateTime pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }
}


