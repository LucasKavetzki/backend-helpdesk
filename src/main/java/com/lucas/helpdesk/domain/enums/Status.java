package com.lucas.helpdesk.domain.enums;

public enum Status {
	
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2,"ENCERRADO");
	
	private Integer codigo;
	private String descricao;
	
	
	
	private Status(Integer codigo, String descricao) {
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
	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		// SE O PERFIL EXISTIR ELE RETORNA
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		//SE DER RUIM
		throw new  IllegalArgumentException("Status inválido");
	}
	
	

}
