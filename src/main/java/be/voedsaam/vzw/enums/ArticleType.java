package be.voedsaam.vzw.enums;

public enum ArticleType {
    NEWS("Nieuws"),
    GOAL("Doelstelling"),
    ABOUT("Over ons"),
    HOME("Start Pagina");

    private String value;
    private ArticleType(String value) {
        this.value = value;
    }
    public String getValue( ) {
        return value;
    }
}
