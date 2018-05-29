package br.info.pweb.chines.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageUtils {

	
	@Autowired
	private ServletContext context;
	
	public String caminhoProjeto() {
		return context.getRealPath("");
	}
	
	
	public void armazenamentoFotoPerfil(String nomeArquivo, byte[] dadosArquivo) throws IOException {
		String caminhoFoto = caminhoProjeto() + "assets/fotos";
		String caminhoArquivo = caminhoFoto + "/" + nomeArquivo;
		
		File pastaFoto = new File(caminhoFoto);
		if(!pastaFoto.exists()) {
			pastaFoto.mkdirs(); 
		}
		
		File arquivoFoto = new File(caminhoArquivo);
		if(arquivoFoto.exists()) {
			arquivoFoto.delete();
		}
		
		arquivoFoto.createNewFile();
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoFoto));
		bos.write(dadosArquivo);
		bos.close();
	}
	
}
