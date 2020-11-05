package br.com.projetoAlexa.services.impl;

import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projetoAlexa.dto.MapaAstralDTOData;
import br.com.projetoAlexa.services.DadosService;

@Service
public class dadosServiceImpl implements DadosService {

	static final String urlBiscoito = "https://www.horoscopovirtual.com.br/biscoito-da-sorte/resultado/";
	static final String urlHoroscopo = "https://www.horoscopovirtual.com.br/horoscopo/";
	static final String urlHoroscopoVirtual = "https://www.horoscopovirtual.com.br/";
	static final String urlDistance = "https://www.adistanciaentre.com/br/%s-latitude-longitude-%s-latitude-%s-longitude/LatLongHistoria/700226.aspx";
	static final String urlMapaAstral = "https://astralmapa.com.br/mapa-astral";
	static final Random random = new Random();

	@Override
	public String dadosBiscoitoDaSorte() throws IOException {
		Document doc = Jsoup.connect(urlBiscoito + random.nextInt(250)).get();
		String mensagem = null;
		Elements newsHeadlines = doc.select("meta[name*=description]");
		for (Element headline : newsHeadlines) {
			mensagem = headline.attr("content");
		}
		return "Essa é mensagem do biscoito da sorte para hoje: " + mensagem + " Números da sorte: "
				+ random.nextInt(100) + ", " + random.nextInt(100) + ", " + random.nextInt(100) + ", "
				+ random.nextInt(100) + ", " + random.nextInt(100);

	}

	@Override
	@Cacheable("signo")
	public String signo(String signo, Boolean isAmanha) throws IOException {

		String url = urlHoroscopo + signo + (isAmanha ? "/amanha" : "");
		Document doc = Jsoup.connect(url).get();

		StringBuilder mensagemFinal = new StringBuilder();
		Elements newsHeadlines = doc.select("section[class*=content]");// name*=description
		for (Element headline : newsHeadlines) {
			mensagemFinal.append("A previsão de ");
			mensagemFinal.append(isAmanha ? "Amanhã " : "Hoje ");
			mensagemFinal.append("para o seu signo é:");
			mensagemFinal.append(headline.getElementsByClass("text-pred").text());
		}
		return mensagemFinal.toString();
	}

	@Override
	@Cacheable("mapa")
	public String mapaAstral(MapaAstralDTOData mapa) throws Exception {
		String url = String.format(urlDistance, mapa.getCity(), mapa.getCity(), mapa.getCity());

		Document doc = Jsoup.connect(url).get();
		String cidade = doc.getElementById("MC_GMD_lblName").text();
		String latitude = doc.getElementById("MC_GMD_lblLatitude").text();
		String longitude = doc.getElementById("MC_GMD_lblLongitude").text();

		if (cidade == null || latitude == null || longitude == null) {
			throw new Exception("Serviço de localização indisponível!");
		}

		Document docMapaAstral = Jsoup.connect(urlMapaAstral).data("name", mapa.getName()).data("city", cidade)
				.data("date", mapa.getDate()).data("hour", mapa.getHour()).data("latitude", latitude.replace(",", "."))
				.data("longitude", longitude.replace(",", ".")).post();

		StringBuilder mensagemFinal = new StringBuilder("");

		String acedente = docMapaAstral.getElementsByClass("hs_about_heading_wrapper").get(2).select("p").text()
				.replace(". Ler Mais", ".");

		StringBuilder posicaoDosAstrosMensagem = new StringBuilder("Posições dos Astros são: ");
		Elements posicaoDosAstros = docMapaAstral
				.select("body > div.hs_sign_main_wrapper > div > div.hs_sign_right_wrapper.visible-xs > div");
		String mensagemPosicaoDosAstros = posicaoDosAstros.get(0).select("div.hs_slider_tabs_icon_cont_wrapper > ul")
				.stream().map(e -> e.text()).collect(Collectors.joining(",   ")).replaceAll("Ângulo", "no ângulo de");
		
		posicaoDosAstrosMensagem.append(mensagemPosicaoDosAstros).append(".");

		StringBuilder distribuicaoDosElementos = new StringBuilder("A distribuição dos Elementos revela que ");
		String distribuicaoDosElementostexto = docMapaAstral.select("body > div:nth-child(21) > div > div > div > div > p").text();
 
		String fogo = "fogo " + docMapaAstral.getElementById("element-fire").attr("value");
		String terra = "terra " + docMapaAstral.getElementById("element-earth").attr("value");
		String ar = "ar " + docMapaAstral.getElementById("element-air").attr("value");
		String agua = "água " + docMapaAstral.getElementById("element-water").attr("value");
		
		distribuicaoDosElementos.append(distribuicaoDosElementostexto).append("E a distribuição dos é:  ").append(fogo + "%, ").append(terra + "%, ").append(ar + "%, ").append(agua + "%.");

		mensagemFinal.append("Mapa Astral de ").append(mapa.getName()).append(", ").append(acedente).append(" ")
				.append(posicaoDosAstrosMensagem).append(distribuicaoDosElementos);

		return mensagemFinal.toString();
	}

	@Override
	@Cacheable("simpatia")
	public String simpatia(String tema, String simpatia) throws IOException {
		Document doc = Jsoup.connect(urlHoroscopoVirtual + tema + "/"+ simpatia ).get();
		Elements newsHeadlines = doc.getElementsByClass("result");
		String title = newsHeadlines.select("h2").text();
		String mensagem = newsHeadlines.select("p").text();

		return title + ": " + mensagem;
	}

}
