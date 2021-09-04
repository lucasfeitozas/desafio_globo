package com.globo.desafio.utils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Util
 * 
 * @author lucasfeitozas
 *
 */
public class FormatadorUtil {

	public static List<String> formataEValidaParaFormatoEmailELogin(final String nome,
			final List<String> listaPalavrasOfensivas) {

		// remove acentos e preposicoes
		String nomeParaComposicao = Normalizer.normalize(nome, Normalizer.Form.NFD);
		String[] nomesParaComposicaoArray = nomeParaComposicao //
				.replaceAll(Constantes.REGEX_ACENTOS, "") //
				.replaceAll(Constantes.REGEX_PREPOSICOES, "$1 $5").split(" ");

		// para cada palavra, remove algarismo romano e palavras ofensivas
		return Arrays.asList(nomesParaComposicaoArray).stream()
				.filter(algarismoRomano -> !algarismoRomano.matches(Constantes.REGEX_ALGARISMO_ROMANO))
				.filter(nomeOfensivo -> {
					Optional<String> findAny = listaPalavrasOfensivas.stream()
							.filter(test -> nomeOfensivo.trim().toUpperCase().equals(test)).findAny();
					return !findAny.isPresent();
				}).map(String::toLowerCase).collect(Collectors.toList());
	}

	public static List<String> formataEValidaParaFormatoDisplayName(final String nome, final List<String> listaPalavrasOfensivas) {

		// remove acentos e preposicoes
		String nomeParaComposicao = Normalizer.normalize(nome, Normalizer.Form.NFD);
		String[] nomesParaComposicaoArray = nomeParaComposicao.replaceAll(Constantes.REGEX_ACENTOS, "").split(" ");

		// busca lista de palavras proibidas
		return Arrays.asList(nomesParaComposicaoArray).stream().filter(ofensiveWord -> {
			Optional<String> findAny = listaPalavrasOfensivas.stream()
					.filter(test -> ofensiveWord.trim().toUpperCase().equals(test)).findAny();
			return !findAny.isPresent();
		}).map(m -> m.substring(0, 1).toUpperCase() + m.substring(1)).collect(Collectors.toList());
	}

}
