package br.com.projetoAlexa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MapaAstralDTOData {
	@JsonProperty("name")
	public String name;
	@JsonProperty("city")
	public String city;
	@JsonProperty("date")
	public String date;
	@JsonProperty("hour")
	public String hour;

}