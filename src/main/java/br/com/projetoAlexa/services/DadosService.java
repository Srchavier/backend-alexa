package br.com.projetoAlexa.services;

import java.io.IOException;

public interface DadosService {
	
	String dadosBiscoitoDaSorte() throws IOException;

	String signo(String signo) throws IOException;

}
