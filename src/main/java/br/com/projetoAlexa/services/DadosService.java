package br.com.projetoAlexa.services;

import java.io.IOException;

import br.com.projetoAlexa.dto.MapaAstralDTOData;

public interface DadosService {
	
	String dadosBiscoitoDaSorte() throws IOException;

	String signo(String signo, Boolean isAmanha) throws IOException;

	String mapaAstral(MapaAstralDTOData mapa) throws IOException, Exception;


}
