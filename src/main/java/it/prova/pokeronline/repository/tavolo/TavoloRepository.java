package it.prova.pokeronline.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloRepository extends JpaSpecificationExecutor<Tavolo>, PagingAndSortingRepository<Tavolo, Long>{
	
	List<Tavolo> findByDenominazione(String denominazione);
	
	

}
