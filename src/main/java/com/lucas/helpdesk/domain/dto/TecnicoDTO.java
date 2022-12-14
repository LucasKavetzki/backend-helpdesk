package com.lucas.helpdesk.domain.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.helpdesk.domain.Pessoa;
import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.domain.enums.Perfil;

public class TecnicoDTO  extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	
	
	protected Integer id;
	
	@NotNull(message= "O campo NOME é Requerido")
	protected String nome;
	
	@NotNull(message= "O campo CPF é Requerido")
	protected String cpf;
	
	@NotNull(message= "O campo EMAIL é Requerido")
	protected String email;
	
	@NotNull(message= "O campo SENHA é Requerido")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	//PADRAO DE DATA
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public TecnicoDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
		addPerfil(Perfil.CLIENTE);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Set<Perfil> getPerfis() { //RETORNA PERFIL EXISTENTE
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}


	

	public void setPerfis(Set<Integer> perfis) {
		this.perfis = perfis;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {  
		this.dataCriacao = dataCriacao;
	}

	
	
	

}
