package be.voedsaam.vzw.enums;

public enum ProductType {
    FEAD("fead"),
    VEGETABLES("Groenten"),
    FRUIT("Fruit"), PARTNER("Partner"),
    MEAT("Vlees"),
    FISH("Vis"),
    DAIRY("Zuivel"),
    PRESERVES("Conserven");

    private String value;
    ProductType(String value) {
        this.value = value;
    }
    public String getValue( ) {
        return value;
    }

}


