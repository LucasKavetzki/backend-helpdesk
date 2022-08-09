package com.lucas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.repositories.TecnicoRepository;
import com.lucas.helpdesk.service.exptions.ObjectnotFouldException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repo;
	
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFouldException("Objeto não encontrado! Id:" + id));
	}

	public List<Tecnico> findAll(){
		return repo.findAll();
	}

	
}
