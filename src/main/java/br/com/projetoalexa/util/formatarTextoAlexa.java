package br.com.projetoalexa.util;

public class formatarTextoAlexa {
	
	
	public static String getTextoAlexa(String texto) {
		return new StringBuilder().append("<speak>").append(texto).append("</speak>").toString();
	}

}
