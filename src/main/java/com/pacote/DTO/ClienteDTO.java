package com.pacote.DTO;
import java.io.Serializable;
import java.util.Date;

import com.pacote.domain.Cliente;
import com.pacote.domain.enums.StatusCliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;	

	private long id;
	private String nome;
	private Date dataCadastro;
	private StatusCliente statusCliente;


	public ClienteDTO(Cliente obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		dataCadastro = obj.getDataCadastro();
		statusCliente = obj.getStatusCliente();
	}

	public ClienteDTO() {}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public StatusCliente getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(StatusCliente statusCliente) {
		this.statusCliente = statusCliente;
	}

	
}
