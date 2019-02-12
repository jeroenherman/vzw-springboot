package be.voedsaam.vzw.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Destination implements Serializable {

	private static final long serialVersionUID = 253747969710711286L;
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(cascade = { CascadeType.ALL })
	private Address address;
	@ElementCollection
	private Collection<String> agreements;
	@OneToMany(cascade = { CascadeType.ALL })
	private Collection<Task> tasks;
	private String contactInfo;
	private String destinationName;
	
	
	public Destination() {
		this.address = new Address();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<String> getAgreements() {
		if (agreements==null)
			agreements= new ArrayList<String>(); 
		return agreements;
	}

	public void setAgreements(Collection<String> agreements) {
		this.agreements = agreements;
	}

	public Collection<Task> getTasks() {
		if (tasks==null)
			tasks = new ArrayList<Task>();
		return tasks;
	}

	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Destination other = (Destination) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
