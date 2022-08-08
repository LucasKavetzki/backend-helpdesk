package com.lucas.helpdesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value= "/tecnicos")
public class TecnicoResource {
	
	
	@Autowired
	private TecnicoService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	

}