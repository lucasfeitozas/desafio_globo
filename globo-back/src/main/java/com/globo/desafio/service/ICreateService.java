package com.globo.desafio.service;

import java.util.List;

/**
 * Interface para criacao de dados
 * @author lucasfeitozas
 *
 */
public interface ICreateService {
	
	/**
	 * Metodo que configura criacao de multiplos dados por nome informado.
	 * @param nome
	 * @return
	 */
	List<String> executarProcesso(String nome, List<String> listaPalavrasOfensivas);

}
