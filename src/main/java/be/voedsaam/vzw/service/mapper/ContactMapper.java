package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.repository.ContactRepository;
import be.voedsaam.vzw.business.repository.TaskRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.ContactDTO;
import be.voedsaam.vzw.service.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContactMapper extends AbstractMapper<Contact, ContactDTO> {

	private ContactRepository contactRepository;
	@Autowired
	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public ContactDTO mapToDTO(Contact b) {
		if (b ==null)
		return null;
		ContactDTO d = new ContactDTO();
		d.setId(b.getId());
		d.setEmail(b.getEmail());
		d.setMessage(b.getMessage());
		d.setName(b.getName());
		d.setSubject(b.getSubject());
		d.setTel(b.getTel());
		return d;
	}

	@Override
	public Contact mapToObj(ContactDTO d) {
		if (d == null)
		return null;
		Contact b = new Contact();
		Optional<Contact> o = Optional.empty();
		if (d.getId()!=null)
			o = contactRepository.findById(d.getId());
		if (o.isPresent())
			b= o.get();
		b.setId(d.getId());
		b.setEmail(d.getEmail());
		b.setMessage(d.getMessage());
		b.setName(d.getName());
		b.setSubject(d.getSubject());
		b.setTel(d.getTel());
		return b;
	}
	

}
