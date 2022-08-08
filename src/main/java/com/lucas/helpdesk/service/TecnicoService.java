package com.lucas.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repo;
	
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
