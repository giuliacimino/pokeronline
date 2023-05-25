package it.prova.pokeronline.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.IdNotNullForInsertException;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import it.prova.pokeronline.web.api.exception.UtenteNotAuthorizedException;

@RestController
@RequestMapping("api/tavolo")
public class TavoloController {

	@Autowired
	TavoloService tavoloService;

	@Autowired
	UtenteService utenteService;

	@GetMapping
	public List<TavoloDTO> getAll() {
		return TavoloDTO.createTavoloDTOListFromModelList(tavoloService.listAll());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TavoloDTO insertNew(@Valid @RequestBody TavoloDTO tavoloInput) {
		
		// controllo che id dell'utente sia se stesso
				String username = SecurityContextHolder.getContext().getAuthentication().getName();
				Utente utenteLoggato = utenteService.findByUsername(username);

				boolean eUtente = false;

				for (Ruolo ruoloItem : utenteLoggato.getRuoli()) {
					if (ruoloItem.getDescrizione().equals("Administrator")) {
						eUtente = true;
					}
				}
				if(utenteLoggato== null || !eUtente)
					throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
				// non sta bene
				if (tavoloInput.getId() != null)
					throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

				Tavolo tavoloInserito = tavoloService.inserisciNuovo(tavoloInput.buildTavoloModel(true));
				return TavoloDTO.buildTavoloDTOFromModel(tavoloInserito);
	}

	@GetMapping(value = "findById/{id}")
	public TavoloDTO findById(@PathVariable(value = "id", required = true) long id) {

		// controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);

		boolean eUtente = false;

		for (Ruolo ruoloItem : utenteLoggato.getRuoli()) {
			if (ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		if(utenteLoggato== null || !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		
		// eseguo codice previsto se autorizzati
		Tavolo tavolo = tavoloService.caricaSingoloTavolo(id);

		if (tavolo == null)
			throw new TavoloNotFoundException("tavolo not found con id: " + id);

		return TavoloDTO.buildTavoloDTOFromModel(tavolo);
	}
	
	@PutMapping(value = "update/{id}")
	public TavoloDTO update(@Valid @RequestBody TavoloDTO tavoloInput, @PathVariable(required = true) Long id) {
		
		//controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		boolean eUtente = false;
		
		for(Ruolo ruoloItem: utenteLoggato.getRuoli()) {
			if(ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		
		if(utenteLoggato== null || !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		
		// eseguo codice previsto se autorizzati
		Tavolo tavolo = tavoloService.caricaSingoloTavolo(id);

		if (tavolo == null)
			throw new TavoloNotFoundException("tavolo not found con id: " + id);

		tavoloInput.setId(id);
		Tavolo tavoloAggiornato = tavoloService.aggiorna(tavoloInput.buildTavoloModel(true));
		return TavoloDTO.buildTavoloDTOFromModel(tavoloAggiornato);
	}
	
	@DeleteMapping(value = "delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void delete(@PathVariable(required = true) Long id) {
		
		//controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);
		
		boolean eUtente = false;
		
		for(Ruolo ruoloItem: utenteLoggato.getRuoli()) {
			if(ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		
		if(utenteLoggato == null || !eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		
		// eseguo codice previsto se autorizzati
		Tavolo tavolo = tavoloService.caricaSingoloTavolo(id);

		if (tavolo == null)
			throw new TavoloNotFoundException("tavolo not found con id: " + id);

		tavoloService.rimuovi(tavolo.getId());
	}
	
	
	@GetMapping(value = "siediti/{id}")
	public TavoloDTO siediti(@PathVariable(required = true) Long id) {
		return TavoloDTO.buildTavoloDTOFromModel(tavoloService.siediti(id));
	}
	
	
	@GetMapping(value = "gioca/{id}")
	public UtenteDTO gioca(@PathVariable(required = true) Long id) {
		return UtenteDTO.buildUtenteDTOFromModel(tavoloService.gioca(id));
	}
	
	@GetMapping(value = "abbandona/{id}")
	public TavoloDTO abbandona(@PathVariable(required = true) Long id) {
		return TavoloDTO.buildTavoloDTOFromModel(tavoloService.abbandona(id));
	}
	
	
	
	
	
	
	
	
}