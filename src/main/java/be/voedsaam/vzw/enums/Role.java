package be.voedsaam.vzw.enums;

public enum Role {
	
	LOGISTICS("Logistiek"),
	COORDINATOR("Coordinator"),
	VOLUNTEER("Vrijwilliger"), PARTNER("Partner"),
	DRIVER("Chauffeur"),
	ATTENDEE("Begeleider"),
	DEPOTHELP("Hulp Depot");
	
	private String value;
	private Role(String value) {
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
