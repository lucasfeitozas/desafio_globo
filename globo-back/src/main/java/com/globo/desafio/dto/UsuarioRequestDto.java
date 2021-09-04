package com.globo.desafio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsuarioRequestDto {
	
	private @NotBlank @Size(min = 3) String nomeCompleto;

}
