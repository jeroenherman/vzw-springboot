package be.voedsaam.vzw.service.dto;



import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;

public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
	private String street;
	private Integer streetNumber;
	private Integer postalCode;
	private String city;
	private String tel;
	private Color color;
	
	public UserDTO() {}
	public UserDTO(String fullName) {
		setFullName(fullName);
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append(getFirstName()).append(" ").append(getLastName());
		return sb.toString();
	}
	
	public void setFullName(String fullName) {
		String [] split = fullName.trim().split(" ");
		if (split.length==2) {
		setFirstName(split[0]);
		setLastName(split[1]);
		}
		if (split.length==3) {
			setFirstName(split[0]);
			setLastName(split[1]+" "+split[2]);
			}
		}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", street=" + street + ", streetNumber=" + streetNumber
				+ ", postalCode=" + postalCode + ", city=" + city + ", tel=" + tel + ", color=" + color + "]";
	}
	
	

}
