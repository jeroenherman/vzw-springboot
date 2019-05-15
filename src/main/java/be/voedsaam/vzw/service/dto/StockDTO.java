package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.ProductType;

import java.util.List;

public class StockDTO {
    private Long id;
    private String name, location;
    private List<String> users;
    private boolean emptyStock;
    private boolean noUsers;

    public boolean isNoUsers() {
        return noUsers;
    }

    public void setNoUsers(boolean noUsers) {
        this.noUsers = noUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public boolean isEmptyStock() {
        return emptyStock;
    }

    public void setEmptyStock(boolean emptyStock) {
        this.emptyStock = emptyStock;
    }
}
