package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Agreement;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.AgreementDTO;
import org.springframework.stereotype.Component;

@Component
public class AgreementMapper extends AbstractMapper<Agreement, AgreementDTO> {
	@Override
	public AgreementDTO mapToDTO(Agreement b) {
		if (b ==null)
		return null;
		AgreementDTO d = new AgreementDTO();
		
		return d;
	}

	@Override
	public Agreement mapToObj(AgreementDTO d) {
		if (d == null)
		return null;
		Agreement b = new Agreement();
		
		return b;
	}

}
