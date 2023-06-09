package it.prova.pokeronline.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.Utente;

public class UtenteDTO {
	private Long id;
	
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	
	@NotBlank(message = "{username.notblank}")
	private String username;
	
	
	@NotBlank(message = "{password.notblank}")
	@JsonIgnore(value = true)
	private String password;
	
	
	@NotNull(message = "{dataRegistrazione.notnull}")
	private LocalDate dataRegistrazione;
	
	private StatoUtente stato;
	
	private Double esperienzaAccumulata;
	
	@NotNull(message = "{creditoAccumulato.notnull}")
	private Double creditoAccumulato;
	
	
	
	private Long[] ruoliIds;

	public UtenteDTO() {
		super();
	}

	public UtenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			@NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato, Long[] ruoliIds) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.creditoAccumulato = creditoAccumulato;
		this.ruoliIds = ruoliIds;
	}

	public UtenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			@NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato, Long[] ruoliIds) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.creditoAccumulato = creditoAccumulato;
		this.ruoliIds = ruoliIds;
	}

	public UtenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			@NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.creditoAccumulato = creditoAccumulato;
	}
	
	

	public UtenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			@NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.creditoAccumulato = creditoAccumulato;
	}
	
	

	public UtenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			Double esperienzaAccumulata, @NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.esperienzaAccumulata = esperienzaAccumulata;
		this.creditoAccumulato = creditoAccumulato;
	}
	
	

	public UtenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{password.notblank}") String password,
			@NotNull(message = "{dataRegistrazione.notnull}") LocalDate dataRegistrazione, StatoUtente stato,
			Double esperienzaAccumulata, @NotNull(message = "{creditoAccumulato.notnull}") Double creditoAccumulato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.esperienzaAccumulata = esperienzaAccumulata;
		this.creditoAccumulato = creditoAccumulato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(LocalDate dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Double getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(Double creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public Long[] getRuoliIds() {
		return ruoliIds;
	}

	public void setRuoliIds(Long[] ruoliIds) {
		this.ruoliIds = ruoliIds;
	}
	
	
	
	public Double getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}

	public void setEsperienzaAccumulata(Double esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}

	public Utente buildUtenteModel(boolean includeIdRoles) {
		Utente result = new Utente(this.id,this.username, this.password, this.nome, this.cognome,
				this.dataRegistrazione,this.stato, this.esperienzaAccumulata, this.creditoAccumulato);
		if (includeIdRoles && ruoliIds != null)
			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));

		return result;
	}
	
	
	// niente password...
		public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
			UtenteDTO result = new UtenteDTO(utenteModel.getId(),utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getUsername(), utenteModel.getPassword(), utenteModel.getDataRegistrazione(), utenteModel.getStato(), utenteModel.getEsperienzaAccumulata(), utenteModel.getCreditoAccumulato());

			if (!utenteModel.getRuoli().isEmpty())
				result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
						.toArray(new Long[] {});

			return result;
		}
		
		
	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelList) {
		return modelList.stream().map(entity -> UtenteDTO.buildUtenteDTOFromModel(entity)).collect(Collectors.toList());
		}



}
