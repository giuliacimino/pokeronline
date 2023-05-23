package it.prova.pokeronline.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;

@Service
@Transactional(readOnly = true)
public class TavoloServiceImpl implements TavoloService{
	
	@Autowired
	TavoloRepository repository;
	
	@Override
	public List<Tavolo> listAll() {
		return (List<Tavolo>) repository.findAll();

	}

	@Override
	public List<Tavolo> cercaPerDenominazione(String denominazione) {
		return repository.findByDenominazione(denominazione);
	}

	@Override
	@Transactional
	public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
		tavoloInstance.setDataCreazione(LocalDate.now());
		return repository.save(tavoloInstance);

	}

	@Override
	@Transactional
	public Tavolo aggiorna(Tavolo tavoloInstance) {
		return repository.save(tavoloInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.findById(idToRemove)
		.orElseThrow(() -> new TavoloNotFoundException("Tavolo not found con id: " + idToRemove));
repository.deleteById(idToRemove);
	}

	@Override
	public Tavolo caricaSingoloTavolo(Long id) {
		return repository.findById(id).orElse(null);

	}


	
	
	
	

}
