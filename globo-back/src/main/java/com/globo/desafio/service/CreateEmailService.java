package com.globo.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.globo.desafio.utils.FormatadorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("createEmailService")
public class CreateEmailService implements ICreateService {
	
	@Override
	public List<String> executarProcesso(String nome, List<String> listaPalavrasOfensivas) {

		List<String> resultado = new ArrayList<>();

		if (nome.length() <= 3) {
			log.info("O nome precisa ter mais de 3 caracteres");
			return resultado;
		}

		List<String> listaNomesParaComporEmail = FormatadorUtil.formataEValidaParaFormatoEmailELogin(nome, listaPalavrasOfensivas);
		if (listaNomesParaComporEmail.size() == 1) {
			return mapMailList(listaNomesParaComporEmail);
		}

		String primeiroNome = listaNomesParaComporEmail.get(0).trim();
		String ultimoNome = listaNomesParaComporEmail.get(listaNomesParaComporEmail.size() - 1).trim();
		resultado.add(primeiroNome + "." + ultimoNome);

		// cria array de nomes do maio e listas de composicao para facilitar formatacao
		// de email
		List<String> listaNomesDoMeio = listaNomesParaComporEmail.size() > 2
				? listaNomesParaComporEmail.subList(0, listaNomesParaComporEmail.size() - 1)
				: new ArrayList<>();
		List<String> listComposicao01 = new ArrayList<>();
		List<String> listComposicao02 = new ArrayList<>();
		List<String> listComposicao03 = new ArrayList<>();

		for (int i = listaNomesDoMeio.size() - 1; i > 0; i--) {
			resultado.add(primeiroNome + "." + listaNomesDoMeio.get(i));
			listComposicao01.add(primeiroNome + "." + listaNomesDoMeio.get(i).substring(0, 1).trim() + ultimoNome);
			listComposicao02.add(primeiroNome.substring(0, 1).trim() + listaNomesDoMeio.get(i).trim());
			listComposicao03.add(primeiroNome.substring(0, 2).trim() + listaNomesDoMeio.get(i).trim());
		}

		resultado.addAll(listComposicao01);
		resultado.add(primeiroNome.substring(0, 1).trim() + ultimoNome);
		resultado.addAll(listComposicao02);
		resultado.add(primeiroNome.substring(0, 2).trim() + ultimoNome);
		resultado.addAll(listComposicao03);
		if (!listaNomesDoMeio.isEmpty()) {
			resultado.add(primeiroNome + "." + listaNomesDoMeio.get(listaNomesDoMeio.size() - 1) + "." + ultimoNome);
		}
		resultado.add(primeiroNome + ultimoNome.substring(0, 1).trim());
		resultado.add(primeiroNome);
		resultado.add(ultimoNome);

		return mapMailList(resultado);
	}

	private static List<String> mapMailList(List<String> listaNomesParaComporEmail) {
		return listaNomesParaComporEmail.stream().map(mail -> mail.toLowerCase().concat("@teste.globo"))
				.collect(Collectors.toList());
	}

}
