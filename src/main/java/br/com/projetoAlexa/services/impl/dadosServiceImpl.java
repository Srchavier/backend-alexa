package br.com.projetoAlexa.services.impl;

import java.io.IOException;

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

	@Override
	public String dadosBiscoitoDaSorte(int id) throws IOException {
		Document doc = Jsoup.connect(urlBiscoito + id).get();
		String mensagem = null;
		Elements newsHeadlines = doc.select("meta[name*=description]");
		for (Element headline : newsHeadlines) {
			mensagem = headline.attr("content");
		}
		return mensagem;

	}

	@Override
	public String signo(String signo) throws IOException {
		Document doc = Jsoup.connect(urlHoroscopo + signo).get();

		StringBuilder mensagemFinal = new StringBuilder();
		Elements newsHeadlines = doc.select("section[class*=content]");//name*=description
		for (Element headline : newsHeadlines) {
			mensagemFinal.append(headline.getElementsByClass("title-sign").text());
			mensagemFinal.append(": ");
			mensagemFinal.append(headline.getElementsByClass("text-pred").text());
		}
		return mensagemFinal.toString();
	}

}
