package br.com.projetoAlexa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoAlexa.dto.MapaAstralDTOData;
import br.com.projetoAlexa.services.DadosService;

@RestController
@RequestMapping("/horoscopo")
public class horoscopoController {

	@Autowired
	private DadosService dados;

	@GetMapping("/biscoito")
	public ResponseEntity<?> sorte() {

		try {
			return ResponseEntity.ok().body(dados.dadosBiscoitoDaSorte());

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

	@GetMapping("/signo/{signo}/{isAmanha}")
	public ResponseEntity<?> signo(@PathVariable("signo") String signo, @PathVariable("isAmanha") Boolean isAmanha) {
		try {
			return ResponseEntity.ok().body(dados.signo(signo, isAmanha));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}
	
	@GetMapping("/simpatia/{tema}/{simpatia}")
	public ResponseEntity<?> simpatia(@PathVariable("tema") String tema, @PathVariable("simpatia") String simpatia) {
		try {
			return ResponseEntity.ok().body(dados.simpatia(tema, simpatia));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

	@PostMapping("/mapa-astral")
	public ResponseEntity<?> signo(@RequestBody MapaAstralDTOData mapa) {
		try {
			return ResponseEntity.ok().body(dados.mapaAstral(mapa));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

}
