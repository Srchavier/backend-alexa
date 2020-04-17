package br.com.projetoAlexa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoAlexa.services.DadosService;

@RestController
@RequestMapping("/horoscopo")
public class horoscopoController {
	
	@Autowired
	private DadosService dados;
	
	@GetMapping("/biscoito")
	public ResponseEntity<?> sorte()  {
		
		
		try {
			return ResponseEntity.ok().body(dados.dadosBiscoitoDaSorte());
					
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}
	
	@GetMapping("/signo/{signo}")
	public ResponseEntity<?> signo(@PathVariable String signo)  {
		try {
			return ResponseEntity.ok().body(dados.signo(signo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

}
