package br.com.projetoAlexa.resources;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoAlexa.services.DadosService;

@RestController
@RequestMapping("/horoscopo")
public class horoscopoController {
	
	@Autowired
	private DadosService dados;
	
	@GetMapping
	public ResponseEntity<?> sorte()  {
		
		Random random = new Random();
		try {
			return ResponseEntity.ok().body(
					"está e sua sorte de hoje! " 
					+ dados.dadosBiscoitoDaSorte(random.nextInt(250))
					+ " Números da sorte:" 
					+ random.nextInt(100)
					+ ", "
					+ random.nextInt(100)
					+", "
					+ random.nextInt(100)
					+", "
					+ random.nextInt(100)
					+", "
					+ random.nextInt(100));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

}
