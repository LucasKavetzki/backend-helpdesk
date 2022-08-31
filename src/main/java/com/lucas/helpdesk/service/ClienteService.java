package com.lucas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Pessoa;
import com.lucas.helpdesk.domain.Cliente;
import com.lucas.helpdesk.domain.dto.ClienteDTO;
import com.lucas.helpdesk.repositories.PessoaRepository;
import com.lucas.helpdesk.repositories.ClienteRepository;
import com.lucas.helpdesk.service.exptions.DataIntegrityViolationException;
import com.lucas.helpdesk.service.exptions.ObjectnotFouldException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private PessoaRepository repop;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFouldException("Objeto não encontrado! Id:" + id));
	}

	public List<Cliente> findAll(){
		return repo.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(pe.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repo.save(newObj);
	
	}
	
	
	// IMPLEMENTAÇÃO PUT, ATUALIZA AS INFORMAÇÕES
			public Cliente update(Integer id,  @Valid ClienteDTO objDTO){
				objDTO.setId(id);
				Cliente oldObj = findById(id);
				validaPorCpfEEmail(objDTO);
				oldObj = new Cliente(objDTO);
				return repo.save(oldObj);
			}
			
			//DELETA UM TECNICO
			public void delete(Integer id) {
				Cliente obj = findById(id);
				if(obj.getChamados().size() > 0) {
					throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser excluido!");
				}
				 repo.deleteById(id);
			}


	//VALIDAR POR CPF
	private void validaPorCpfEEmail(ClienteDTO objDTO) {
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