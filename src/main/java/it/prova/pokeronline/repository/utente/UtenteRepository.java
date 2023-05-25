package it.prova.pokeronline.repository.utente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{
	

	@EntityGraph(attributePaths = { "ruoli" })
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	Utente findByUsernameAndPassword(String username, String password);
	
	//caricamento eager, ovviamente si può fare anche con jpql
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
	
	
	
	@Query(value = "select distinct u.* from Utente u inner join Tavolo t on t.utenteCreatore_id = u.id where t.dataCreazione < u.dataRegistrazione", nativeQuery= true)
	public List<Utente> trovaErroriUtenti ();
	
	@Modifying
	@Query(value = "delete g.* from tavolo_giocatori g inner join tavolo t on t.id = g.Tavolo_id inner join utente u on u.id = g.giocatori_id\r\n"
			+ "where g.Tavolo_id in (select u.id from utente u where u.username in :listaUsername);", nativeQuery = true)
			public void svuotaImmediatamenteTavoliCreatiDaUtenti (List<String> listaUsername);

}
