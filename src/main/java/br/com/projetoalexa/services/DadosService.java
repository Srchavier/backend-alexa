package br.com.projetoalexa.services;

import java.io.IOException;

import br.com.projetoalexa.dto.MapaAstralDTOData;

public interface DadosService {
	
	String dadosBiscoitoDaSorte() throws IOException;

	String signo(String signo, Boolean isAmanha) throws IOException;

	String mapaAstral(MapaAstralDTOData mapa) throws IOException, Exception;

	String simpatia(String tema, String simpatia) throws IOException;


}
