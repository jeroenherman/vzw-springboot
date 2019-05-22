package be.voedsaam.vzw.enums;

public enum Color {
	RED("Rood", "#ff0000"),
	BLUE("Blauw","#0000cc" ),
	GREEN("Groen","#00cc00" ),
	YELLOW("Geel", "#ffff00"),
	PURPLE("Paars","#6600cc"),
	ORANGE("Oranje","#ff6600"),
	
	LIGHTRED("Licht Rood", "#ff8080"),
	LIGHTBLUE("Licht Blauw","#8080ff"),
	LIGHTGREEN("Licht Groen", "#80ff80"),
	LIGHTYELLOW("Licht Geel", "#ffff80"),
	LIGHTPURPLE("Licht Paars","#bf80ff"),
	LIGHTORANGE("Licht Oranje","#ffb380" ),
	LIGHTGREY("Licht Grijs","#bfbfbf"),
	
	DARKRED("Donker Rood", "#800000"),
	DARKBLUE("Donker Blauw","#000080"),
	DARKGREEN("Donker Groen", "#008000"),
	DARKYELLOW("Donker Geel", "#808000"),
	DARKPURPLE("Donker Paars", "#400080"),
	DARKORANGE("Donker Oranje", "#804d00"),
	DARKGREY("Donker Grijs","#404040" ),
	BLACK("Zwart","#000000" ),
	WHITE("Wit", "#ffffff");
	
	private String value, hex;
	private Color(String value, String hex ) {
		this.value = value;
		this.hex = hex;
	} 
	public String getValue( ) {
		return value;
	}

	public String getHex() {
		return hex;
	}

	@Override
	public String toString() {
		
		return value;
	}
}
