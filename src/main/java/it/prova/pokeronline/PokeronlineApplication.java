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

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", Ruolo.ROLE_CLASSIC_USER));
		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now(), 100);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", LocalDate.now(), 200);
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Antonioo", "Verdii", LocalDate.now(), 20);
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", LocalDate.now(), 150);
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		
		Tavolo tavolo1 = new Tavolo(20, 10, "tavolo per principianti", LocalDate.of(2023, 3, 12));
		tavolo1.getGiocatori().add(utenteServiceInstance.findByUsername("classicUser"));
		if (tavoloServiceInstance.cercaPerDenominazione(tavolo1.getDenominazione()).isEmpty())
		tavoloServiceInstance.inserisciNuovo(tavolo1);
		
		Tavolo tavolo2 = new Tavolo(200, 50, "tavolo per giocatori medi", LocalDate.of(2023, 4, 15));
		tavolo2.getGiocatori().add(utenteServiceInstance.findByUsername("classicUser"));
		tavolo2.getGiocatori().add(utenteServiceInstance.findByUsername("classicUser1"));
		if (tavoloServiceInstance.cercaPerDenominazione(tavolo2.getDenominazione()).isEmpty())
		tavoloServiceInstance.inserisciNuovo(tavolo2);
		
		
		
		
		
		
		
	}

}
