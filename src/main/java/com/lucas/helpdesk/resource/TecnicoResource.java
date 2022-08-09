package com.lucas.helpdesk.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.domain.dto.TecnicoDTO;
import com.lucas.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value= "/tecnicos")
public class TecnicoResource {
	
	
	@Autowired
	private TecnicoService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
		
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> list  = service.findAll();
		// METODO DTO PUXA SÓ OBJETOS DA CLASSE EM QUSTÃO E NADA MAIS
		List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);	
	}	
	

}
