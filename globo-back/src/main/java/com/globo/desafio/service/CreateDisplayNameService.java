package com.globo.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.globo.desafio.utils.Constantes;
import com.globo.desafio.utils.FormatadorUtil;

@Service
@Qualifier("createDisplayNameService")
public class CreateDisplayNameService implements ICreateService{

	@Override
	public List<String> executarProcesso(String nome, List<String> listaPalavrasOfensivas) {
		List<String> resultado = new ArrayList<>();
		
		List<String> criaEFormataDisplayNames = FormatadorUtil.formataEValidaParaFormatoDisplayName(nome, listaPalavrasOfensivas);
		if (criaEFormataDisplayNames.size() == 1) {
			return criaEFormataDisplayNames;
		}
		String primeiroNome = criaEFormataDisplayNames.get(0).trim();
		String ultimoNome = criaEFormataDisplayNames.get(criaEFormataDisplayNames.size() - 1).trim();

		resultado.add(primeiroNome + " " + ultimoNome);
		List<String> listaNomesDoMeio = criaEFormataDisplayNames.size() > 2 ?criaEFormataDisplayNames.subList(0, criaEFormataDisplayNames.size() - 1): new ArrayList<>();
		
		List<String> listCombinacao01 = new ArrayList<>();
		List<String> listCombinacao02 = new ArrayList<>();
		
		String formatoDados = null;
		for (int i = listaNomesDoMeio.size() - 1; i > 0; i--) {
			String currentName = listaNomesDoMeio.get(i);
			
			if (!currentName.matches(Constantes.REGEX_PREPOSICAO_DISPLAY_NAME)) {
				resultado.add(primeiroNome + " " + currentName);
				listCombinacao01.add(primeiroNome + " " + currentName + " " + ultimoNome);
				listCombinacao02.add(primeiroNome + " " + currentName.substring(0,1) + ultimoNome.toLowerCase());
			} else {
				formatoDados = primeiroNome + " "+ currentName +" " + listaNomesDoMeio.get(i+1);
			}
			
		}
		if (formatoDados!= null)
			resultado.add(formatoDados);
		listCombinacao01.forEach(resultado::add);
		resultado.add(criaEFormataDisplayNames.stream().filter( e ->  !e.matches(Constantes.REGEX_PREPOSICAO_DISPLAY_NAME)).collect(Collectors.joining(" ")) );
		listCombinacao02.forEach(resultado::add);
		resultado.add(criaEFormataDisplayNames.stream().collect(Collectors.joining(" ")) );
		resultado.add(criaEFormataDisplayNames.stream().collect(Collectors.joining(" ")) );

		return resultado;
	}

}
