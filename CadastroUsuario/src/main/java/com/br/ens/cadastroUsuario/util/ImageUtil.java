package com.br.ens.cadastroUsuario.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUtil {

	@Value("${diretorio.usuarios.fotos}")
	private String diretorioFotos;
	
	private static final Logger logger = Logger.getLogger(ImageUtil.class.getName());

	public String salvarFotoUsuario(MultipartFile foto, String nomeUsuario, Long id) {
		Path diretorioPath = Paths.get(diretorioFotos);
		String fileType = getFileType(foto.getOriginalFilename());
		String fileName = nomeUsuario + id + fileType;
		Path arquivoPath = diretorioPath.resolve(fileName);

		try {
			Files.createDirectories(diretorioPath);
			foto.transferTo(arquivoPath);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Erro ao salvar foto do usuario");
			e.printStackTrace();
		}
		return fileName;

	}

	public String getCaminhoImagem(String fileName) {
		String path="";
		if(fileName !=null) {
			path= diretorioFotos + fileName;
		}
		return path;
	}

	private String getFileType(String originalFileName) {
		if (originalFileName.contains(".")) {
			return "."+originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

}
