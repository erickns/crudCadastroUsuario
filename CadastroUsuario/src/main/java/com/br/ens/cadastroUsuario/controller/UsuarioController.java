package com.br.ens.cadastroUsuario.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.ens.cadastroUsuario.dto.UsuarioDTO;
import com.br.ens.cadastroUsuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public List<UsuarioDTO> listar() {
		return usuarioService.listar();
	}
	
	@GetMapping("/buscar/{id}")
	public UsuarioDTO buscar(@PathVariable("id")Long id) {
		return usuarioService.buscar(id);
	}
	
	@PostMapping("/incluir")
	public UsuarioDTO incluirUsuario(@RequestBody UsuarioDTO dto) {
		return this.usuarioService.salvar(dto);
	}
	
	//metodo a ser usado pelo frontEnd tanto par incluir com para atualizar foto
	@PostMapping("/salvarFoto/{userId}")
	public UsuarioDTO incluirFoto(@PathVariable("userId")Long userId, @RequestBody MultipartFile foto) {
		return this.usuarioService.incluirFoto(foto,userId);
	}
	
	@PutMapping("/atualizar/{id}")
	public UsuarioDTO atualizarUsuario(@PathVariable("id")Long id, @RequestBody UsuarioDTO dto) {
		return this.usuarioService.atualizar(dto,id);
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable("id")Long id) {
		this.usuarioService.deletar(id);
	}


}
