package it.prova.pokeronline.dto;

import java.util.List;

public class SvuotaTavoliDTO {
	
	public String username;

	public SvuotaTavoliDTO() {
		super();
	}

	public SvuotaTavoliDTO(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
