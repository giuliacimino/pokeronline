package it.prova.pokeronline.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloRepository extends JpaSpecificationExecutor<Tavolo>, PagingAndSortingRepository<Tavolo, Long>{
	
	List<Tavolo> findByDenominazione(String denominazione);
	
	@Query(value= "SELECT  t.*"
			+ "FROM tavolo t inner join utente u"
			+ "on t.utente_id =u.id"
			+ "WHERE u.username like : username and exists(select null from utente u where u.esperienzaaccumulata>=0);", nativeQuery= true)
	 List<Tavolo> estraiTavoliConAlmenoUnUtenteAlDiSopraDiSoglia(String username, int soglia);
	 
	
	@Query(value = "select t.* from utente u inner join tavolo_giocatori g on u.id = g.giocatori_id "
			+ "inner join tavolo t on g.Tavolo_id = t.id group by t.id order by sum(u.esperienzaaccumulata) desc limit 1", nativeQuery = true)
	 Tavolo trovaTavoloConMassimaEsperienzaGiocatori ();
	
	

}
