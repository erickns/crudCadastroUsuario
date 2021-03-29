package com.br.ens.cadastroUsuario.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long codUsuario;
	
	private String nome;
	@Temporal(value = TemporalType.DATE)
	private Date dtNasc;
	private String dsNomFoto;
	
	public Long getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getDsNomFoto() {
		return dsNomFoto;
	}
	public void setDsNomFoto(String foto) {
		this.dsNomFoto = foto;
	}
	@Override
	public String toString() {
		return "Usuario {"
				+ "codUsuario:" + codUsuario + ", "
				+ "nome:" + nome + ", "
				+ "dtNasc:" + dtNasc + ", "
				+ "dsNomFoto:" + dsNomFoto 
				+ "}";
	}
	
	
	

}
