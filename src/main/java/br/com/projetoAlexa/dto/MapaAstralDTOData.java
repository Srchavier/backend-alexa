package br.com.projetoAlexa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MapaAstralDTOData {
	@Schema(description = "Nome", required = true, example = "Eduardo")
	@JsonProperty("name")
	public String name;
	@Schema(description = "cidade de nascimento", required = true, example = "brasilia")
	@JsonProperty("city")
	public String city;
	@Schema(description = "data de nascimento", required = true, example = "03/03/1993")
	@JsonProperty("date")
	public String date;
	@Schema(description = "hora de nascimento", required = true, example = "10:40")
	@JsonProperty("hour")
	public String hour;

}