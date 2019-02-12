package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO>{

	@Override
	public UserDTO mapToDTO(User b) {
		if (b==null)
			return null;
		UserDTO dto = new UserDTO();
		dto.setId(b.getId());
		dto.setPassword(b.getPassword());
		dto.setFirstName(b.getFirstName());
		dto.setLastName(b.getLastName());
		dto.setEmail(b.getEmail());
		dto.setTel(b.getTel());
		dto.setColor(b.getColor());
		dto.setRole(b.getRole());
		
		if (!(b.getAddress()==null)) { 
		dto.setCity(b.getAddress().getCity());
		dto.setStreet(b.getAddress().getStreet());
		dto.setStreetNumber(b.getAddress().getNumber());
		dto.setPostalCode(b.getAddress().getPostalCode());
		}
		
		return dto;
	}

	@Override
	public User mapToObj(UserDTO d) {
		if (d==null)
			return null;
		User user = new User();
		Address address = new Address();
		user.setId(d.getId());
		user.setPassword(d.getPassword());
		user.setFirstName(d.getFirstName());
		user.setLastName(d.getLastName());
		user.setEmail(d.getEmail());
		address.setCity(d.getCity());
		address.setStreet(d.getStreet());
		address.setNumber(d.getStreetNumber());
		address.setPostalCode(d.getPostalCode());
		user.setTel(d.getTel());
		user.setColor(d.getColor());
		user.setRole(d.getRole());
		user.setAddress(address);
		return user;
	}
	

}
