package com.br.ens.cadastroUsuario.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.br.ens.cadastroUsuario.model.Usuario;

public class UsuarioDTO {

	private Long codUsuario;
	private String nome;
	private Date dtNasc;
	private String urlDownloadFoto;
	
	public static UsuarioDTO convert (Usuario usuario,String urlDownloadFoto) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setCodUsuario(usuario.getCodUsuario());
		dto.setDtNasc(usuario.getDtNasc());
		dto.setNome(usuario.getNome());
		dto.setUrlDownloadFoto(urlDownloadFoto != null ? urlDownloadFoto:"");
		return dto;
	}
	
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
	
	public String getUrlDownloadFoto() {
		return urlDownloadFoto;
	}
	public void setUrlDownloadFoto(String urlDownloadFoto) {
		this.urlDownloadFoto = urlDownloadFoto;
	}

	@Override
	public String toString() {
		return "UsuarioDTO {codUsuario=" + codUsuario 
				+ ", nome=" + nome 
				+ ", dtNasc=" + dtNasc 
				+ ", urlDownloadFoto="
				+ urlDownloadFoto 
				+ "}";
	}
	
}
