package com.globo.desafio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesafioResponseDto {

	private List<String> emailsDisponiveis;
	private List<String> loginsDisponiveis;
	private List<String> displayNames;
}
