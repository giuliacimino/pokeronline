package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;

public interface TavoloService{
	List<Tavolo> cercaPerDenominazione (String denominazione);
	
	List<Tavolo> listAll();
	
	Tavolo inserisciNuovo(Tavolo tavoloInstance);
	
	Tavolo aggiorna (Tavolo tavoloInstance);
	
	void rimuovi (Long idToRemove);
	
	Tavolo caricaSingoloTavolo (Long id);
	
	
	public Tavolo siediti(Long idTavolo);
	
	public Utente gioca(Long idTavolo);
	
	public Tavolo abbandona (Long idTavolo);
	
	public List<Tavolo> estraiTavoliConAlmenoUnUtenteAlDiSopraDiSoglia(String usernamePassato, int soglia);

	

}
