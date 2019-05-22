package be.voedsaam.vzw.service.dto;

import java.util.List;

public class TransferDTO {
    private Long idFromStock;
    private Long idToStock;
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public Long getIdFromStock() {
        return idFromStock;
    }

    public void setIdFromStock(Long idFromStock) {
        this.idFromStock = idFromStock;
    }

    public Long getIdToStock() {
        return idToStock;
    }

    public void setIdToStock(Long idToStock) {
        this.idToStock = idToStock;
    }
}
