package br.com.projetoAlexa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
	@JsonProperty("timestamp")
	private Date timestamp;
	@JsonProperty("status")
	private int status;
	@JsonProperty("error")
	private String error;
	@JsonProperty("message")
	private String message;
	@JsonProperty("path")
	private String path;

}
