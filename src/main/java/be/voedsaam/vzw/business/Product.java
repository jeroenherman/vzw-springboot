package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.ProductType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Product extends AbstractDomainClass{
    private String name, description, deliveryNr;
    private Double unitOfMeasure;
    private LocalDate schelfLife;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @ManyToMany
    private Set<Stock> stocks = new HashSet<>();

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

    public LocalDate getSchelfLife() {
        return schelfLife;
    }

    public void setSchelfLife(LocalDate schelfLife) {
        this.schelfLife = schelfLife;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public void removeStock(Stock stock){
        stocks.remove(stock);
    }

    public Set<Stock> getStocks(){
        return Collections.unmodifiableSet(stocks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productType);
    }
}
