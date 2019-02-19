package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User extends AbstractDomainClass {

	private String firstName;
	private String lastName;
	private String email;
	private String tel;
	@Enumerated(EnumType.STRING)
	private Color color;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToOne(cascade = {CascadeType.ALL})
	private Address address;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {	CascadeType.MERGE})
	@JoinTable(name = "USER_DRIVE",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "drive_id") })
	private List<Drive> drives = new ArrayList<>();
	@ManyToOne
	private Schedule schedule;

	public User() {

	}
	
	public User(String fullName) {
		if (fullName!=null)
		setFullName(fullName);
	}

	public User(String firstName, String lastName, String email, String tel) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.tel = tel;
	}

	public User(String firstName, String lastName, String email, String tel,  Address address, Role role,  Color color) {
		this(firstName, lastName, email, tel);
		this.color = color;
		this.role = role;
		this.address = address;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public List<Drive> getDrives() {
		return Collections.unmodifiableList(drives);
	}
	public void addDrive(Drive drive){
		if(!drives.contains(drive)){
			drives.add(drive);
			drive.addUser(this);
		}
	}
	public void removeDrive(Drive drive){
		if(drives.contains(drive)){
			drive.removeUser(this);
			drives.remove(drive);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Schedule getSchedule() {
		return schedule;
	}
}
