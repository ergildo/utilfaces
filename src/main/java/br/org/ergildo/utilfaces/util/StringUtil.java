package br.org.ergildo.utilfaces.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * Classe de apoio a manipula��o de Strings.
 * 
 * @author ergildo.dias
 * 
 */
public final class StringUtil {

	/**
	 * 
	 */
	private StringUtil() {
	}

	/**
	 * Verifica se a String informada est� vazia, considerando tamb�m o null
	 * como vazio.
	 * 
	 * @return
	 */
	public static boolean isVazia(String string) {

		return StringUtils.isEmpty(string);
	}

	/**
	 * Verifica se a String informada � diferente de vazia.
	 * 
	 * @return
	 */
	public static boolean isDiferenteDeVazio(String string) {

		return !isVazia(string);
	}

	/**
	 * Retorna um valor String aleat�rio com 6 caracteres.
	 */
	public static String getValorRandomico() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 6);
	}

	/**
	 * Verifica se os Caracteres de uma String informada s�o diferentes.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean possuiCaracteresDiferentes(String string) {

		if (isVazia(string)) {
			return false;
		}

		char primeiro = string.charAt(0);

		for (char proximo : string.toCharArray()) {

			if (primeiro != proximo) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Remove da String informada o(s) caracter(s) n�o num�rico(s).
	 * 
	 * @param string
	 * @return
	 */
	public static String removerCaracterNaoNumerico(String string) {

		if (isVazia(string)) {
			return string;
		}

		String numero = string.replaceAll("\\D", "");

		return numero;
	}

	/**
	 * Verifica se as duas strings informadas s�o iguais, ignorando diferen�as
	 * entre letras mai�sculas ou min�sculas.
	 * 
	 * @param stringA
	 * @param stringB
	 * @return
	 */
	public static boolean equalsIgnoreCase(String stringA, String stringB) {

		return StringUtils.equalsIgnoreCase(stringA, stringB);
	}

	/**
	 * Verifica se as duas strings informadas s�o iguais.
	 * 
	 * @param stringA
	 * @param stringB
	 * @return
	 */
	public static boolean equals(String stringA, String stringB) {

		return StringUtils.equals(stringA, stringB);
	}

	/**
	 * Concatena o(s) objeto(s) informado(s).
	 * 
	 * @param objetos
	 * @return
	 */
	public static String concatenar(Object... objetos) {

		return StringUtils.join(objetos).toString();
	}

}
