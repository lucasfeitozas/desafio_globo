package com.globo.desafio.utils;

public class Constantes {
	public static final String REGEX_ACENTOS = "[^\\p{ASCII}]";
	public static final String REGEX_PREPOSICOES = "(?i)(\\w)(\\s+)(e|do|da|do|das|de|di|du)(\\s+)(\\w)";
	public static final String REGEX_PREPOSICAO_DISPLAY_NAME  = "(?i)(e|do|da|do|das|de|di|du|dos)";
	public static final String REGEX_ALGARISMO_ROMANO = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

}
