package be.voedsaam.vzw.service.dto;

import java.util.List;

public class DestinationDTO {
	private Long id;
	private String street;
	private Integer streetNumber;
	private Integer postalCode;
	private String city;
	private String contactInfo;
	private String destinationName;
	private List<String> agreements;
	private boolean drives;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public List<String> getAgreements() {
		return agreements;
	}
	public void setAgreements(List<String> agreements) {
		this.agreements = agreements;
	}

	public boolean isDrives() {
		return drives;
	}

	public void setDrives(boolean drives) {
		this.drives = drives;
	}
}
