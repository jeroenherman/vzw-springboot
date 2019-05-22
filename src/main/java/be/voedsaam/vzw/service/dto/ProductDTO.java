package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.ProductType;

public class ProductDTO {
    private Long id;
    private String name, description, deliveryNr, shelfLife;
    private Double unitOfMeasure;
    private ProductType productType;
    private boolean inStock;
    private Integer qty;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryNr() {
        return deliveryNr;
    }

    public void setDeliveryNr(String deliveryNr) {
        this.deliveryNr = deliveryNr;
    }

    public Double getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(Double unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
