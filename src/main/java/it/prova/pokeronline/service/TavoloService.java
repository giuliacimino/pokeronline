package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService{
	List<Tavolo> cercaPerDenominazione (String denominazione);
	
	List<Tavolo> listAll();
	
	Tavolo inserisciNuovo(Tavolo tavoloInstance);
	
	Tavolo aggiorna (Tavolo tavoloInstance);
	
	void rimuovi (Long idToRemove);
	
	Tavolo caricaSingoloTavolo (Long id);

}
