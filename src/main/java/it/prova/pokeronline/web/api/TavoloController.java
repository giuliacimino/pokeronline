package it.prova.pokeronline.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.service.TavoloService;

@RestController
@RequestMapping("api/tavolo")
public class TavoloController {
	
	@Autowired
	TavoloService tavoloService;
	
	
	@GetMapping
	public List<TavoloDTO> getAll(){
		return TavoloDTO.createTavoloDTOListFromModelList(tavoloService.listAll());
	}

}
