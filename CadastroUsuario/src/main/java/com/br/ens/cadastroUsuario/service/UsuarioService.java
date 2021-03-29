package com.br.ens.cadastroUsuario.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.ens.cadastroUsuario.dto.UsuarioDTO;
import com.br.ens.cadastroUsuario.model.Usuario;
import com.br.ens.cadastroUsuario.repository.UsuarioRepository;
import com.br.ens.cadastroUsuario.util.ImageUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	ImageUtil imageUtil;

	private static final Logger logger = Logger.getLogger(ImageUtil.class.getName());

	public List<UsuarioDTO> listar() {
		return repository.findAll().stream()
				.map((u) -> UsuarioDTO.convert(u, imageUtil.getCaminhoImagem(u.getDsNomFoto())))
				.collect(Collectors.toList());
	}

	public UsuarioDTO buscar(Long id) {
		UsuarioDTO dtoRetorno = null;
		try {
			Usuario user = repository.findById(id).get();
			dtoRetorno = UsuarioDTO.convert(user, user.getDsNomFoto());
		} catch (NullPointerException nullPointerException) {
			logger.log(Level.WARNING, "Usuario não existe");
			return dtoRetorno;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Erro ao salvar foto do usuario");
			e.printStackTrace();
		}
		return dtoRetorno;
	}

	public UsuarioDTO salvar(UsuarioDTO dto) {
		Usuario user = new Usuario();
		user.setCodUsuario(dto.getCodUsuario()!=null ? dto.getCodUsuario():null);
		user.setNome(dto.getNome()!=null ? dto.getNome():null);
		user.setDtNasc(dto.getDtNasc()!=null ? dto.getDtNasc():null);
		user.setDsNomFoto(dto.getUrlDownloadFoto()!=null ? dto.getUrlDownloadFoto():null);
		this.repository.save(user).getCodUsuario();
		return UsuarioDTO.convert(user, imageUtil.getCaminhoImagem(user.getDsNomFoto()));
	}

	public UsuarioDTO incluirFoto(MultipartFile foto, Long id) {
		Usuario user = repository.getOne(id);
		user.setDsNomFoto(imageUtil.salvarFotoUsuario(foto, user.getNome(), id));
		user = repository.save(user);
		return UsuarioDTO.convert(user, imageUtil.getCaminhoImagem(user.getDsNomFoto()));
	}

	public UsuarioDTO atualizar(UsuarioDTO dto, Long id) {
		UsuarioDTO user = this.buscar(id);
		user.setCodUsuario(dto.getCodUsuario()!=null ? dto.getCodUsuario():user.getCodUsuario());
		user.setNome(dto.getNome()!=null ? dto.getNome():user.getNome());
		user.setDtNasc(dto.getDtNasc()!=null ? dto.getDtNasc():user.getDtNasc());
		user.setUrlDownloadFoto(dto.getUrlDownloadFoto()!=null ? dto.getUrlDownloadFoto():user.getUrlDownloadFoto());
		return this.salvar(user);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}

}
