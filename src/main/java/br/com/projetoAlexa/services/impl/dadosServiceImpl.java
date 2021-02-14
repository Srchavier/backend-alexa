package br.com.projetoAlexa.services.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.projetoAlexa.dto.EstadoDto;
import br.com.projetoAlexa.dto.MapaAstralDTOData;
import br.com.projetoAlexa.services.DadosService;
import br.com.projetoAlexa.util.Http3Service;

@Service
public class dadosServiceImpl implements DadosService {

	static final String urlBiscoito = "https://www.horoscopovirtual.com.br/biscoito-da-sorte/resultado/";
	static final String urlHoroscopo = "https://www.horoscopovirtual.com.br/horoscopo/";
	static final String urlHoroscopoVirtual = "https://www.horoscopovirtual.com.br/";
	static final String urlDistance = "https://astralmapa.com.br/location?query=%s";
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

		EstadoDto estado = (EstadoDto) Http3Service.BuscaServicoDeOutrasApi(String.format(urlDistance, mapa.getCity()),
				EstadoDto.class);

		String place = estado.getPredictions().get(0).getPlace_id();
		String description = estado.getPredictions().get(0).getDescription();
		Document docMapaAstral = Jsoup.connect(urlMapaAstral).data("name", mapa.getName()).data("date", mapa.getDate())
				.data("hour", mapa.getHour()).data("place", place).data("description", description).data("city", description).post();

		StringBuilder mensagemFinal = new StringBuilder("");

		String acedente = docMapaAstral.getElementsByClass("container").select("p").get(0).text();
		
		

		StringBuilder posicaoDosAstrosMensagem = new StringBuilder("Posições dos Astros são: ");
		

		String mensagemPosicaoDosAstros = docMapaAstral.getElementsByClass("container").get(3).getElementsByClass("hs_sign_right_wrapper").select("li")
				.stream().map(e -> e.text().replace("\"", "\", ")).collect(Collectors.joining("")).replaceAll("Ângulo", "no ângulo de").concat(acedente);

		posicaoDosAstrosMensagem.append(mensagemPosicaoDosAstros).append(".");

		StringBuilder distribuicaoDosElementos = new StringBuilder("A distribuição dos Elementos revela que ");
		
		String distribuicaoDosElementostexto = docMapaAstral.getElementsByClass("container").get(2).select("p").get(0).text();

		String fogo = "fogo " + docMapaAstral.getElementById("element-fire").attr("value");
		String terra = "terra " + docMapaAstral.getElementById("element-earth").attr("value");
		String ar = "ar " + docMapaAstral.getElementById("element-air").attr("value");
		String agua = "água " + docMapaAstral.getElementById("element-water").attr("value");

		distribuicaoDosElementos.append(distribuicaoDosElementostexto).append("E a distribuição dos é:  ")
				.append(fogo + "%, ").append(terra + "%, ").append(ar + "%, ").append(agua + "%.");

		mensagemFinal.append("Mapa Astral de ").append(mapa.getName()).append(", ").append(acedente).append(" ")
				.append(posicaoDosAstrosMensagem).append(distribuicaoDosElementos);

		return mensagemFinal.toString();
	}

	@Override
	@Cacheable("simpatia")
	public String simpatia(String tema, String simpatia) throws IOException {
		Document doc = Jsoup.connect(urlHoroscopoVirtual + tema + "/" + simpatia).get();
		Elements newsHeadlines = doc.getElementsByClass("result");
		String title = newsHeadlines.select("h2").text();
		String mensagem = newsHeadlines.select("p").text();

		return title + ": " + mensagem;
	}

}

class ParameterStringBuilder {
	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
