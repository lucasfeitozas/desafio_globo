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
@Qualifier("createLoginService")
public class CreateLoginService implements ICreateService {

	@Override
	public List<String> executarProcesso(String nome, List<String> listaPalavrasOfensivas) {
		
		List<String> resultado = new ArrayList<>();
		
		if (nome.length() <= 3) {
			log.info("O nome precisa ter mais de 3 caracteres");
			return resultado;
		}
		
		List<String> listaNomesParaComporLogin = FormatadorUtil.formataEValidaParaFormatoEmailELogin(nome, listaPalavrasOfensivas);
		
		if (listaNomesParaComporLogin.size()==1) {
			return listaNomesParaComporLogin.stream().map(String::toLowerCase).collect(Collectors.toList());
		}
		
		String primeiroNome = listaNomesParaComporLogin.get(0).trim();
		String ultimoNome = listaNomesParaComporLogin.get(listaNomesParaComporLogin.size() - 1).trim();

		addLogin(resultado, primeiroNome);
		addLogin(resultado, ultimoNome);
		addLogin(resultado, primeiroNome.substring(0, 1).trim() + ultimoNome);

		// cria array de nomes do maio e listas de composicao para facilitar formatacao de email
		List<String> listaNomesDoMeio = listaNomesParaComporLogin.size() > 2 ?listaNomesParaComporLogin.subList(0, listaNomesParaComporLogin.size() - 1): new ArrayList<>();
		List<String> listComposicao01 = new ArrayList<>();
		List<String> listComposicao02 = new ArrayList<>();
		List<String> listComposicao03 = new ArrayList<>();

		for (int i = listaNomesDoMeio.size() - 1; i > 0; i--) {
			addLogin(resultado, primeiroNome.substring(0, 1).trim() + listaNomesDoMeio.get(i).trim());
			
			listComposicao01.add(primeiroNome + listaNomesDoMeio.get(i).substring(0,1));
			listComposicao02.add(primeiroNome.substring(0, 1).trim() + listaNomesDoMeio.get(i).trim());
			listComposicao03.add(primeiroNome + listaNomesDoMeio.get(i).substring(0, 1).trim());
		}

		addLogin(resultado, primeiroNome + ultimoNome.substring(0,1));
		listComposicao01.forEach( item -> addLogin(resultado, item));

		addLogin(resultado, subtr8posicoes(primeiroNome));
		addLogin(resultado, subtr8posicoes(ultimoNome));
		addLogin(resultado, subtr8posicoes(primeiroNome.substring(0,1) + ultimoNome));
		
		
		listComposicao02.forEach( item -> addLogin(resultado,subtr8posicoes( item)));
		addLogin(resultado, subtr8posicoes(primeiroNome + ultimoNome.substring(0,1)));
		listComposicao03.forEach( item -> addLogin(resultado, item));

		return resultado;
	}

	private void addLogin(List<String> resultado, String loginValue) {
		if (loginValue.trim().length()>=6 && loginValue.trim().length()<=8)
			resultado.add(loginValue.trim());  
		else
			log.info("Tamanho login inválido");
	}
	

	private String subtr8posicoes(String value) {
		if (value.length()>8) {
			return value.substring(0,8);
		}
		return value;
	}
}
