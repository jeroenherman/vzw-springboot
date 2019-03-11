package be.voedsaam.vzw.enums;

public enum  OrderStatus {
    NEW("Nieuw"),
    IN_PROGRESS("In process"),
    COMPLETED("Afgehandeld"),
    CLOSED("Afgesloten"),
    CANCELLED("Geannulleerd");

    private String value;
    OrderStatus(String value) {
        this.value = value;
    }
    public String getValue( ) {
        return value;
    }

}
