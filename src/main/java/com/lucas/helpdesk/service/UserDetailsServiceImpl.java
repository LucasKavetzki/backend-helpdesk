package com.lucas.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.Security.UserSS;
import com.lucas.helpdesk.domain.Pessoa;
import com.lucas.helpdesk.repositories.PessoaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private  PessoaRepository perepo;
	
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	Optional<Pessoa> pessoa = perepo.findByEmail(email);
    	
    	
    				if (pessoa.isPresent()) {
    				return new UserSS(pessoa.get().getId(), pessoa.get().getEmail(), pessoa.get().getSenha(), pessoa.get().getPerfis());
    				}	
    					throw new UsernameNotFoundException(email);
     				    		
 
    							 
    							 
    						
    	        
    }
    	
        
}  
        	
        
    


	


