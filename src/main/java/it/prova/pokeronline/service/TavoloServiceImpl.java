package it.prova.pokeronline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import it.prova.pokeronline.web.api.exception.CreditoInsufficientePerGiocareException;
import it.prova.pokeronline.web.api.exception.EsperienzaInsuficienteException;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;

@Service
@Transactional(readOnly = true)
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	TavoloRepository repository;

	@Autowired
	UtenteService utenteService;

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
		if (tavoloInstance.getUtenteCreatore() == null) {

			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Utente utenteLoggato = utenteService.findByUsername(username);
			tavoloInstance.setUtenteCreatore(utenteLoggato);
		}

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

	@Override
	public Tavolo siediti(Long idTavolo) {

		Tavolo tavolo = this.caricaSingoloTavolo(idTavolo);

		if (tavolo.getId() == null) {
			throw new TavoloNotFoundException("tavolo non trovato.");
		}

		Set<Utente> giocatori = tavolo.getGiocatori();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);

		if (utenteLoggato.getCreditoAccumulato() == null) {
			utenteLoggato.setCreditoAccumulato(0d);
		}

		if (utenteLoggato.getCreditoAccumulato() < tavolo.getCifraMin()) {
			throw new CreditoInsufficientePerGiocareException("credito non sufficiente per giocare a questo tavolo.");
		}

		if (utenteLoggato.getEsperienzaAccumulata() == null) {
			utenteLoggato.setEsperienzaAccumulata(0d);
		}

		if (utenteLoggato.getEsperienzaAccumulata() < tavolo.getEsperienzaMin()) {
			throw new EsperienzaInsuficienteException(
					"l'esperienza giocatore è insufficiente per giocare a questo tavolo.");
		}

		giocatori.add(utenteLoggato);

		return tavolo;

	}

	@Override
	public Utente gioca(Long idTavolo) {
		Tavolo tavolo = this.caricaSingoloTavolo(idTavolo);
		if (tavolo.getId() == null) {
			throw new TavoloNotFoundException("tavolo non trovato.");
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		if (utenteLoggato.getCreditoAccumulato()< tavolo.getCifraMin()) {
			throw new CreditoInsufficientePerGiocareException("credito insufficiente per giocare.");
		}
		
		
		if (utenteLoggato.getEsperienzaAccumulata() < tavolo.getEsperienzaMin()) {
			throw new EsperienzaInsuficienteException("esperienza insufficiente per giocare.");
		}
		
		if (!tavolo.getGiocatori().contains(utenteLoggato)) {
			throw new GiocatoreNonSeduto("impossibile giocare, il giocatore non è seduto.");
		}
		Double segno = Math.random();
		if (segno <0.5) {
			segno = -1.0;
			
		} 
		Double somma=(Double)Math.random()*1000;
		Double totDaAggiungereOSottrarre = segno*somma;
		
		

	}

}
