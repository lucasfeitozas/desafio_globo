package com.globo.desafio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.globo.desafio.dto.DesafioResponseDto;
import com.globo.desafio.service.DesafioServico;

@SpringBootTest
public class DesafioServiceTest {

	@Autowired
	private DesafioServico desafioServico;

	@Test
	public void deveCriarCombinacoesComNomeCompleto() throws IOException, URISyntaxException {
		String nome = "Davison Henrique de Oliveira Tintel";
		DesafioResponseDto criaCombinacoes = desafioServico.criaCombinacoes(nome);

		assertNotNull(criaCombinacoes);
		assertTrue(criaCombinacoes.getDisplayNames().size() > 0);
		assertTrue(criaCombinacoes.getEmailsDisponiveis().size() > 0);
		assertTrue(criaCombinacoes.getLoginsDisponiveis().size() > 0);
	}

	@Test
	public void deveCriarCombinacoesComNomeSobrenome() throws IOException, URISyntaxException {
		String nome = "Davison Tintel";
		DesafioResponseDto criaCombinacoes = desafioServico.criaCombinacoes(nome);
		assertNotNull(criaCombinacoes);
		assertTrue(criaCombinacoes.getDisplayNames().size() > 0);
		assertTrue(criaCombinacoes.getEmailsDisponiveis().size() > 0);
		assertTrue(criaCombinacoes.getLoginsDisponiveis().size() > 0);

	}

	@Test
	public void deveCriarCombinacoesComNome() throws IOException, URISyntaxException {
		String nome = "Davison";
		DesafioResponseDto criaCombinacoes = desafioServico.criaCombinacoes(nome);
		assertNotNull(criaCombinacoes);
		assertTrue(criaCombinacoes.getDisplayNames().size() > 0);
		assertTrue(criaCombinacoes.getEmailsDisponiveis().size() > 0);
		assertTrue(criaCombinacoes.getLoginsDisponiveis().size() > 0);

	}
}
