package com.globo.desafio.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.globo.desafio.dto.DesafioResponseDto;
import com.globo.desafio.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DesafioServico {
	private final ICreateService createEmailService;
	private final ICreateService createLoginService;
	private final ICreateService createDisplayNameService;
	private final Utils utils;
	

	@Autowired
	public DesafioServico(
			@Qualifier("createEmailService") ICreateService createEmailService, 
			@Qualifier("createLoginService") ICreateService createLoginService,
			@Qualifier("createDisplayNameService") ICreateService createDisplayNameService, 
			Utils utils) {
		super();
		this.createEmailService = createEmailService;
		this.createLoginService = createLoginService;
		this.createDisplayNameService = createDisplayNameService;
		this.utils = utils;
	}

	public DesafioResponseDto criaCombinacoes(String nome) throws IOException, URISyntaxException {
		
		log.info("cria lista de combinacoes por parametro");
		List<String> listaBloqueio = utils.getListaBloqueio();
		createDisplayNameService.executarProcesso(nome, listaBloqueio);
		
		return DesafioResponseDto.builder()
				.displayNames(createDisplayNameService.executarProcesso(nome, listaBloqueio))
				.emailsDisponiveis(createEmailService.executarProcesso(nome, listaBloqueio))
				.loginsDisponiveis(createLoginService.executarProcesso(nome, listaBloqueio))
				.build();
	}


}
