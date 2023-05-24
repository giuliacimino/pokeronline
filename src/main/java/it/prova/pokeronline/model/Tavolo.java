package it.prova.pokeronline.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "tavolo")
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "esperienzaMin")
	private Integer esperienzaMin;
	
	@Column(name = "cifraMin")
	private Integer cifraMin;
	
	@Column(name = "denominazione")
	private String denominazione;
	
	@Column(name = "dataCreazione")
	private LocalDate dataCreazione;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Utente> giocatori= new HashSet<>(0);
	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Utente utenteCreatore;


	public Tavolo() {
		super();
	}


	public Tavolo(Long id, Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione,
			Set<Utente> giocatori, Utente utenteCreatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.giocatori = giocatori;
		this.utenteCreatore = utenteCreatore;
	}
	
	


	public Tavolo(Integer esperienzaMin, Integer cifraMin, String denominazione) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
	}


	public Tavolo(Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione,
			Set<Utente> giocatori, Utente utenteCreatore) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.giocatori = giocatori;
		this.utenteCreatore = utenteCreatore;
	}
	
	



	public Tavolo(Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}
	
	
	
	


	public Tavolo(Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione,
			Utente utenteCreatore) {
		super();
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenteCreatore = utenteCreatore;
	}


	public Tavolo(Long id, Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}


	public Tavolo(Long id, Integer esperienzaMin, Integer cifraMin, String denominazione, LocalDate dataCreazione,
			Utente utenteCreatore) {
		super();
		this.id = id;
		this.esperienzaMin = esperienzaMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenteCreatore = utenteCreatore;
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


	public Set<Utente> getGiocatori() {
		return giocatori;
	}


	public void setGiocatori(Set<Utente> giocatori) {
		this.giocatori = giocatori;
	}


	public Utente getUtenteCreatore() {
		return utenteCreatore;
	}


	public void setUtenteCreatore(Utente utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}
	
	

}
