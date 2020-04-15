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

	@Override
	public String dadosBiscoitoDaSorte(int id) throws IOException {
		Document doc = Jsoup.connect("https://www.horoscopovirtual.com.br/biscoito-da-sorte/resultado/" + id).get();
		String mensagem = null;
		log(doc.title());
		Elements newsHeadlines = doc.select("meta[name*=description]");
		for (Element headline : newsHeadlines) {
			mensagem = headline.attr("content");
		}
		return mensagem;

	}

	private static void log(String msg, String... vals) {
		System.out.println(String.format(msg, vals));
	}

}
