package it.prova.pokeronline;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;


@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private TavoloService tavoloServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PokeronlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Player", Ruolo.ROLE_PLAYER));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
		}
		
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now(), 100.0);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("player1") == null) {
			Utente player1 = new Utente("player1", "player1", "Antonio", "Verdi", LocalDate.now(), 200.0);
			player1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player1);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player1.getId());
		}

		if (utenteServiceInstance.findByUsername("specialPlayer1") == null) {
			Utente specialPlayer1 = new Utente("specialPlayer1", "specialPlayer1", "Antonioo", "Verdii", LocalDate.now(), 20.0);
			specialPlayer1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
			utenteServiceInstance.inserisciNuovo(specialPlayer1);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(specialPlayer1.getId());
		}

		if (utenteServiceInstance.findByUsername("player2") == null) {
			Utente player2 = new Utente("player2", "player2", "Antoniooo", "Verdiii", LocalDate.now(), 150.0);
			player2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player2);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player2.getId());
		}
		
//		Tavolo tavolo1 = new Tavolo(20, 10, "tavolo per principianti", LocalDate.of(2023, 3, 12));
//		tavolo1.getGiocatori().add(utenteServiceInstance.findByUsername("player2"));
//		if (tavoloServiceInstance.cercaPerDenominazione(tavolo1.getDenominazione()).isEmpty()) {
//		
//			tavolo1.setUtenteCreatore(utenteServiceInstance.findByUsername("Administrator"));
//			tavoloServiceInstance.inserisciNuovo(tavolo1);
//		}
//
//		
//		Tavolo tavolo2 = new Tavolo(200, 50, "tavolo per giocatori medi", LocalDate.of(2023, 4, 15));
//		tavolo2.getGiocatori().add(utenteServiceInstance.findByUsername("specialPlayer1"));
//		tavolo2.getGiocatori().add(utenteServiceInstance.findByUsername("player1"));
//		if (tavoloServiceInstance.cercaPerDenominazione(tavolo2.getDenominazione()).isEmpty()) {
//			tavolo2.setUtenteCreatore(utenteServiceInstance.findByUsername("Administrator"));
//			tavoloServiceInstance.inserisciNuovo(tavolo2);
//		}

		
		
		
		
		
		
		
	}

}
