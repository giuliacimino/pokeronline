package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.repository.ruolo.RuoloRepository;

@Service
@Transactional(readOnly = true)
public class RuoloServiceImpl implements RuoloService{
	
	@Autowired
	RuoloRepository repository;

	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>) repository.findAll();

	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);

	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Ruolo ruoloInstance) {
		repository.save(ruoloInstance);

		
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return repository.findByDescrizioneAndCodice(descrizione, codice);

	}

}
