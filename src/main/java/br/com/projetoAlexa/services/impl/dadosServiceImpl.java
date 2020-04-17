package br.com.projetoAlexa.services.impl;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.com.projetoAlexa.services.DadosService;

@Service
public class dadosServiceImpl implements DadosService {

	static final String urlBiscoito = "https://www.horoscopovirtual.com.br/biscoito-da-sorte/resultado/";
	static final String urlHoroscopo = "https://www.horoscopovirtual.com.br/horoscopo/";
	static final Random random = new Random();
	
	
	@Override
	public String dadosBiscoitoDaSorte() throws IOException {
		Document doc = Jsoup.connect(urlBiscoito + random.nextInt(250)).get();
		String mensagem = null;
		Elements newsHeadlines = doc.select("meta[name*=description]");
		for (Element headline : newsHeadlines) {
			mensagem = headline.attr("content");
		}
		return "Essa é mensagem do biscoito da sorte para hoje: " 
				+  mensagem + " Números da sorte: " 
				+ random.nextInt(100)
				+ ", "
				+ random.nextInt(100)
				+", "
				+ random.nextInt(100)
				+", "
				+ random.nextInt(100)
				+", "
				+ random.nextInt(100);

	}

	@Override
	public String signo(String signo) throws IOException {
		Document doc = Jsoup.connect(urlHoroscopo + signo).get();

		StringBuilder mensagemFinal = new StringBuilder();
		Elements newsHeadlines = doc.select("section[class*=content]");//name*=description
		for (Element headline : newsHeadlines) {
			mensagemFinal.append("A previsão para o seu signo é: ");
			mensagemFinal.append(headline.getElementsByClass("text-pred").text());
		}
		return mensagemFinal.toString();
	}

}
