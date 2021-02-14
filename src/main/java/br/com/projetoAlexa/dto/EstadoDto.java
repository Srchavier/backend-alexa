package br.com.projetoAlexa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstadoDto {
	private List<PredictionDTO> predictions = new ArrayList<PredictionDTO>();
	private String status;

	// Getter Methods

	public String getStatus() {
		return status;
	}

	// Setter Methods

	public void setStatus(String status) {
		this.status = status;
	}
}

