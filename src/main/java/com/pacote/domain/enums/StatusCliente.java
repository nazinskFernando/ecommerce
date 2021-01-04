package com.pacote.domain.enums;

public enum StatusCliente {
	 	ATIVO(0, "Ativo"),
	    INATIVO(1, "Inativo");

	    private int cod;
		private String descricao;
		
		private StatusCliente(int cod, String descricao) {
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

