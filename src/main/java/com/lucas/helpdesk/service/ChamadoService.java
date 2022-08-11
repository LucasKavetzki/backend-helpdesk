package com.lucas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Chamado;
import com.lucas.helpdesk.repositories.ChamadoRepository;
import com.lucas.helpdesk.service.exptions.ObjectnotFouldException;
@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repo;
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFouldException("Objeto não encontrado! Id:" + id));
	}


	public List<Chamado> findAll() {
		return repo.findAll();
	}

	
	
}
