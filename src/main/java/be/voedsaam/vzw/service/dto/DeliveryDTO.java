package be.voedsaam.vzw.service.dto;

import java.util.List;

public class DeliveryDTO {
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
