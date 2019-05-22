package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.repository.ContactRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.ContactDTO;
import be.voedsaam.vzw.service.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DonationMapper extends AbstractMapper<Contact, DonationDTO> {

	private ContactRepository contactRepository;
	@Autowired
	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public DonationDTO mapToDTO(Contact b) {
		if (b ==null)
		return null;
		DonationDTO d = new DonationDTO();
		d.setId(b.getId());
		d.setEmail(b.getEmail());
		d.setMessage(b.getMessage());
		d.setName(b.getName());
		d.setSubject(b.getSubject());
		d.setTel(b.getTel());
		return d;
	}

	@Override
	public Contact mapToObj(DonationDTO d) {
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
		StringBuilder sb = new StringBuilder();
		sb.append("Beste graag zou ik een ").append(d.getReoccuringType()).append(" donatie ")
				.append("met een bedrag van ").append(d.getAmount()).append(" euro ")
				.append("willen toekennen aan VoedSaam Vzw. ")
				.append("Dit bedrag zou ik willen overmaken via: ").append(d.getPaymentType());

		b.setMessage(sb.toString());
		b.setName(d.getName());
		b.setSubject("Toekenning Donatie");
		b.setTel(d.getTel());
		return b;
	}
	

}
