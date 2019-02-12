package be.voedsaam.vzw.service.dto;

public class AgreementDTO {
	private Long id;
	private String agreement;

	public String getAgreement() {

		return agreement;
	}

	public void setId(Long id) {
		this.id = id;

	}

	public Long getId() {

		return id;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;

	}

}
