package com.lucas.helpdesk.domain.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	
	private String email;
	private String Senha;
	

	public CredenciaisDTO() {
		
	}
	
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
	
}
