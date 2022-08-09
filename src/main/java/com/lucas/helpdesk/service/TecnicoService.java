package com.lucas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Pessoa;
import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.domain.dto.TecnicoDTO;
import com.lucas.helpdesk.repositories.PessoaRepository;
import com.lucas.helpdesk.repositories.TecnicoRepository;
import com.lucas.helpdesk.service.exptions.DataIntegrityViolationException;
import com.lucas.helpdesk.service.exptions.ObjectnotFouldException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repo;
	@Autowired
	private PessoaRepository repop;

	
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFouldException("Objeto não encontrado! Id:" + id));
	}

	public List<Tecnico> findAll(){
		return repo.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repo.save(newObj);
	
	}
	
	
	// IMPLEMENTAÇÃO PUT, ATUALIZA AS INFORMAÇÕES
			public Tecnico update(Integer id,  @Valid TecnicoDTO objDTO){
				objDTO.setId(id);
				Tecnico oldObj = findById(id);
				validaPorCpfEEmail(objDTO);
				oldObj = new Tecnico(objDTO);
				return repo.save(oldObj);
			}
			
			//DELETA UM TECNICO
			public void delete(Integer id) {
				Tecnico obj = findById(id);
				if(obj.getChamados().size() > 0) {
					throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser excluido!");
				}
				 repo.deleteById(id);
			}


	//VALIDAR POR CPF
	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = repop.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}
		
		//Validar EMAIL
		obj = repop.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado!");
	}

		
		
	
}

	}