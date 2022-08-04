package com.lucas.helpdesk.domain.enums;

public enum Prioridade {
	
	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"),
	ALTA(2,"ALTA");
	
	private Integer codigo;
	private String descricao;
	
	
	
	private Prioridade(Integer codigo, String descricao) {
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
	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		// SE O PERFIL EXISTIR ELE RETORNA
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		//SE DER RUIM
		throw new  IllegalArgumentException("Prioridade inválida");
	}
	
	

}
