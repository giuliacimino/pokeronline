package it.prova.pokeronline.web.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.IdNotNullForInsertException;
import it.prova.pokeronline.web.api.exception.UtenteNotAuthorizedException;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
	
	@Autowired
	UtenteService utenteService;
	
	@PostMapping(value = "compraCredito")
	public UtenteDTO ricaricaCredito(@Valid @RequestBody Map<String, Double>rawValue) {
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
		
		Utente utenteConCredito= utenteService.compraCredito(rawValue.get("cifraRicarica"));
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteConCredito);		
		
	}
	
	
	@GetMapping
	public List<UtenteDTO> listAll(){
		
		// controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);

		
		boolean eUtente = false;

		for (Ruolo ruoloItem : utenteLoggato.getRuoli()) {
			if (ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		if(!eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti());
	}
	
	
	@PostMapping
	public UtenteDTO insertNew(Utente utenteInstance) {
		
		// controllo che id dell'utente sia se stesso
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteLoggato = utenteService.findByUsername(username);

		boolean eUtente = false;

		for (Ruolo ruoloItem : utenteLoggato.getRuoli()) {
			if (ruoloItem.getDescrizione().equals("Administrator")) {
				eUtente = true;
			}
		}
		if(!eUtente)
			throw new UtenteNotAuthorizedException("Non si e' autorizzati all'operazione.");
		
		if (utenteInstance.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		 Utente utenteInserito= utenteService.inserisciNuovo(utenteInstance);
		
		return UtenteDTO.buildUtenteDTOFromModel(utenteInserito);
	}
	
	@PutMapping(value = "edit/{id}")
	public UtenteDTO modifica (Long id) {
		return UtenteDTO.buildUtenteDTOFromModel(utenteService.aggiorna(id));
	}
	
	
	

		
		
		
		
		
		
	}



