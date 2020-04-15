package br.com.projetoAlexa.resources;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoAlexa.services.DadosService;
import br.com.projetoAlexa.util.formatarTextoAlexa;

@RestController
@RequestMapping("/horoscopo")
public class horoscopoController {
	
	@Autowired
	private DadosService dados;
	
	@GetMapping
	public ResponseEntity<?> sorte()  {
		
		Random random = new Random();
		try {
			return ResponseEntity.ok().body(formatarTextoAlexa.getTextoAlexa(dados.dadosBiscoitoDaSorte(random.nextInt(250))));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(formatarTextoAlexa.getTextoAlexa("Sistema temporariamente indisponível, tente mais tarde!"));
		}
	}

}
