package com.globo.desafio.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globo.desafio.dto.DesafioResponseDto;
import com.globo.desafio.dto.UsuarioRequestDto;
import com.globo.desafio.service.DesafioServico;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("v1/desafio")
public class DesafioController {

	private final DesafioServico desafioServico;

	@PostMapping
	public ResponseEntity<DesafioResponseDto> executarDesafio(@RequestBody @Valid  UsuarioRequestDto request) throws IOException, URISyntaxException {
		DesafioResponseDto criaCombinacoes = desafioServico.criaCombinacoes(request.getNomeCompleto());
		
		return ResponseEntity.ok(criaCombinacoes);
		
	}
}
