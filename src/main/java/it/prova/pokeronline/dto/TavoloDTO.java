package it.prova.pokeronline.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public class TavoloDTO {
	
	private Long id;
	
	
	@NotNull(message = "esperienzaMin.notnull}")
	private Integer esperienzaMin;
	
	
	@NotNull(message = "{cifraMin.notnull}")
	private Integer cifraMin;
	
	
	@NotBlank(message = "{denominazione.notblank}")
	private String denominazione;
	
	
	@NotNull(message = "{dataCreazione.notnull}")
	private LocalDate dataCreazione;
	
	
	private Long[] giocatoriIds;
	
	private UtenteDTO utenteCreatore;

	public TavoloDTO(Long id, @NotNull(message = "esperienzaMin.notnull}") Integer esperienzaMin,
			@NotNull(message = "{cifraMin.notnull}") Integer cifraMin,
			@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione, Long[] giocatoriIds,
			UtenteDTO utenteCreatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.giocatoriIds = giocatoriIds;
		this.utenteCreatore = utenteCreatore;
	}
	
	

	public TavoloDTO(Long id, @NotNull(message = "esperienzaMin.notnull}") Integer esperienzaMin,
			@NotNull(message = "{cifraMin.notnull}") Integer cifraMin,
			@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione, UtenteDTO utenteCreatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenteCreatore = utenteCreatore;
	}



	public TavoloDTO(@NotNull(message = "esperienzaMin.notnull}") Integer esperienzaMin,
			@NotNull(message = "{cifraMin.notnull}") Integer cifraMin,
			@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione, Long[] giocatoriIds,
			UtenteDTO utenteCreatore) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.giocatoriIds = giocatoriIds;
		this.utenteCreatore = utenteCreatore;
	}

	public TavoloDTO(@NotNull(message = "esperienzaMin.notnull}") Integer esperienzaMin,
			@NotNull(message = "{cifraMin.notnull}") Integer cifraMin,
			@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}
	
	

	public TavoloDTO(Long id, @NotNull(message = "esperienzaMin.notnull}") Integer esperienzaMin,
			@NotNull(message = "{cifraMin.notnull}") Integer cifraMin,
			@NotBlank(message = "{denominazione.notblank}") String denominazione,
			@NotNull(message = "{dataCreazione.notnull}") LocalDate dataCreazione) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEsperienzaMin() {
		return esperienzaMin;
	}

	public void setEsperienzaMin(Integer esperienzaMin) {
		this.esperienzaMin = esperienzaMin;
	}

	public Integer getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(Integer cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Long[] getGiocatoriIds() {
		return giocatoriIds;
	}

	public void setGiocatoriIds(Long[] giocatoriIds) {
		this.giocatoriIds = giocatoriIds;
	}

	public UtenteDTO getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(UtenteDTO utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}
	
	
	public Tavolo buildTavoloModel(boolean includeGiocatori) {
		Tavolo result = new Tavolo(this.id, this.esperienzaMin, this.cifraMin, this.denominazione, this.dataCreazione);
		if (includeGiocatori && giocatoriIds != null)
			result.setGiocatori(Arrays.asList(giocatoriIds).stream().map(id -> new Utente(id)).collect(Collectors.toSet()));

		return result;
	}
	
	
	
	public static TavoloDTO buildUtenteDTOFromModel(Tavolo tavoloModel) {
		TavoloDTO result = new TavoloDTO (tavoloModel.getId(), tavoloModel.getEsperienzaMin(), tavoloModel.getCifraMin(),
				tavoloModel.getDenominazione(), tavoloModel.getDataCreazione());

		if (!tavoloModel.getGiocatori().isEmpty())
			result.giocatoriIds = tavoloModel.getGiocatori().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	
	



}
