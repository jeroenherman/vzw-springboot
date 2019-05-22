package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, UserDTO>{

	private UserRepository userRepository;
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO mapToDTO(Employee b) {
		if (b==null)
			return null;
		UserDTO dto = new UserDTO();
		dto.setId(b.getId());
		dto.setPassword(b.getPassword());
		dto.setFirstName(b.getFirstName());
		dto.setLastName(b.getLastName());
		dto.setEmail(b.getEmail());
		dto.setTel(b.getTel());
		if (b.getColor()!=null)
			dto.setColor(b.getColor());
		else dto.setColor(Color.LIGHTGREY);
		dto.setRole(b.getRole());
		if (!(b.getAddress()==null)) { 
		dto.setCity(b.getAddress().getCity());
		dto.setStreet(b.getAddress().getStreet());
		dto.setStreetNumber(b.getAddress().getNumber());
		dto.setPostalCode(b.getAddress().getPostalCode());
		}
		dto.setSchedules(b.getSchedules().stream().map(schedule -> schedule.getName()).collect(Collectors.toList()));
		return dto;
	}

	@Override
	public Employee mapToObj(UserDTO d) {
		if (d==null)
			return null;
		User b = new Employee();
		Optional<User> o = Optional.empty();
		if (d.getId()!=null)
			o= userRepository.findById(d.getId());
		if(o.isPresent())
			b = o.get();
		
		Address address = new Address();
		b.setId(d.getId());
		b.setPassword(d.getPassword());
		b.setFirstName(d.getFirstName());
		b.setLastName(d.getLastName());
		b.setEmail(d.getEmail());
		address.setCity(d.getCity());
		address.setStreet(d.getStreet());
		address.setNumber(d.getStreetNumber());
		address.setPostalCode(d.getPostalCode());
		b.setTel(d.getTel());
		b.setColor(d.getColor());
		b.setRole(d.getRole());
		b.setAddress(address);
		return (Employee) b;
	}
	

}
