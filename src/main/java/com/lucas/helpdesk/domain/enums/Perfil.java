package com.lucas.helpdesk.domain.enums;

public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"),
	CLIENTE(1, "ROLE_CLIENTE"),
	TECNICO(2,"ROLE_TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}



	public Integer getCodigo() {
		return codigo;
	}



	public String getDescricao() {
		return descricao;
	}
	
	// SE NÃO EXISTIR O CODIGO RETORNA NULO
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		// SE O PERFIL EXISTIR ELE RETORNA
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		//SE DER RUIM
		throw new  IllegalArgumentException("Perfil inválido");
	}
	
	

}
