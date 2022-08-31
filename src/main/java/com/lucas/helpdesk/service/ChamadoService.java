package com.lucas.helpdesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Chamado;
import com.lucas.helpdesk.domain.Cliente;
import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.domain.dto.ChamadoDTO;
import com.lucas.helpdesk.domain.enums.Prioridade;
import com.lucas.helpdesk.domain.enums.Status;
import com.lucas.helpdesk.repositories.ChamadoRepository;
import com.lucas.helpdesk.service.exptions.ObjectnotFouldException;


@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repo;
	@Autowired
	private TecnicoService tecServer;
	@Autowired
	private ClienteService cliServer;
	
	
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFouldException("Objeto não encontrado! Id:" + id));
	}


	public List<Chamado> findAll() {
		return repo.findAll();
	}

	public Chamado create(ChamadoDTO objDTO) {
		return repo.save(newChamado(objDTO));
	
	}
	

	public Chamado update( Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repo.save(oldObj);
	}
	
	
	
	
	//RECEBE UM CHAMADO
	private Chamado newChamado(ChamadoDTO obj){
		Tecnico tecnico = tecServer.findById(obj.getTecnico());
		Cliente cliente = cliServer.findById(obj.getCliente());
		
		// CONSTROI UM NOVO CHAMADO
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
			
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
			
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
		
		
		
		
	}
	
	
	
		
}
