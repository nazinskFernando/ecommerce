package com.pacote.domain.enums;

public enum StatusEntrega {


    CANCELADO(0, "Cancelado"),
    ANDAMENTO(1, "Andamento"),
    ENTREGUE(2, "Entregue"),
    PENDENTE(3, "Pendente");

    private int cod;
	private String descricao;
	
	private StatusEntrega(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusPedido toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusPedido x : StatusPedido.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

