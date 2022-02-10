package br.com.projetoalexa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public
class PredictionDTO {
	private String place_id;
	private String description;
}