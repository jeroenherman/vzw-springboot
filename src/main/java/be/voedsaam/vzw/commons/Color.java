package be.voedsaam.vzw.commons;

public enum Color {
	RED("Rood"),
	BLUE("Blauw"),
	GREEN("Groen"), 
	YELLOW("Geel"),
	PURPLE("Paars"),
	ORANGE("Oranje"),
	
	LIGHTRED("Licht Rood"),
	LIGHTBLUE("Licht Blauw"),
	LIGHTGREEN("Licht Groen"), 
	LIGHTYELLOW("Licht Geel"),
	LIGHTPURPLE("Licht Paars"),
	LIGHTORANGE("Licht Oranje"),
	LIGHTGREY("Licht Grijs"),
	
	DARKRED("Donker Rood"),
	DARKBLUE("Donker Blauw"),
	DARKGREEN("Donker Groen"), 
	DARKYELLOW("Donker Geel"),
	DARKPURPLE("Donker Paars"),
	DARKORANGE("Donker Oranje"),
	DARKGREY("Donker Grijs"),
	BLACK("Zwart"),
	WHITE("Wit");
	
	private String value;
	private Color(String value) {
		this.value = value;
	} 
	public String getValue( ) {
		return value;
	}
	@Override
	public String toString() {
		
		return value;
	}
}
