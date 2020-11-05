package br.com.projetoAlexa.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoAlexa.dto.ErrorDTO;
import br.com.projetoAlexa.dto.MapaAstralDTOData;
import br.com.projetoAlexa.services.DadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "horoscopo", description = "the horoscopo API")
@RestController
@RequestMapping("/horoscopo")
public class horoscopoController {

	@Autowired
	private DadosService dados;

	@Operation(summary = "Consulta pelo biscoito da sorte.", description = "descobrir sua sorte")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no servidor.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }) })
	@GetMapping("/biscoito")
	public ResponseEntity<?> sorte() {

		try {
			return ResponseEntity.ok().body(dados.dadosBiscoitoDaSorte());

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

	@Operation(summary = "Previsão do seu signo.", description = "previsão pasra seu signo.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no servidor.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }) })
	@GetMapping("/signo/{signo}/{isAmanha}")
	public ResponseEntity<?> signo(
			@Parameter(description = "Signo da pessoa.", required = true) @PathVariable("signo") String signo,
			@Parameter(description = "previsão de hoje ou amanhã", required = true) @PathVariable("isAmanha") Boolean isAmanha) {
		try {
			return ResponseEntity.ok().body(dados.signo(signo, isAmanha));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

	@Operation(summary = "Buscar por uma simpatia.", description = "Buscar por uma simpatia do tipo saúde, nergócios, trabalho, amor.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no servidor.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }) })
	@GetMapping("/simpatia/{tema}/{simpatia}")
	public ResponseEntity<?> simpatia(
			@Parameter(description = "tema da simpatia.", required = true) @PathVariable("tema") String tema,
			@Parameter(description = "a simpatia.", required = true) @PathVariable("simpatia") String simpatia) {
		try {
			return ResponseEntity.ok().body(dados.simpatia(tema, simpatia));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

	@Operation(summary = "Criar um mapa astral.", description = "Criar um mapa astral personalizado de acordo com as informações passadas.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no servidor.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)) }) })
	@PostMapping(value = "/mapa-astral", consumes = { "application/json" })
	public ResponseEntity<?> signo(
			@Parameter(description = "Criar um mapa astral", required = true) @Valid @RequestBody MapaAstralDTOData mapa) {
		try {
			return ResponseEntity.ok().body(dados.mapaAstral(mapa));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Sistema temporariamente indisponível, tente mais tarde!");
		}
	}

}
