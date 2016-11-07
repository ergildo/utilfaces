package br.org.ergildo.utilfaces.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * Classe de apoio a manipulação de Strings.
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
	 * Verifica se a String informada está vazia, considerando também o null
	 * como vazio.
	 * 
	 * @return
	 */
	public static boolean isVazia(String string) {

		return StringUtils.isEmpty(string);
	}

	/**
	 * Verifica se a String informada é diferente de vazia.
	 * 
	 * @return
	 */
	public static boolean isDiferenteDeVazio(String string) {

		return !isVazia(string);
	}

	/**
	 * Retorna um valor String aleatório com 6 caracteres.
	 */
	public static String getValorRandomico() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 6);
	}

	/**
	 * Verifica se os Caracteres de uma String informada são diferentes.
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
	 * Remove da String informada o(s) caracter(s) não numérico(s).
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
	 * Verifica se as duas strings informadas são iguais, ignorando diferenças
	 * entre letras maiúsculas ou minúsculas.
	 * 
	 * @param stringA
	 * @param stringB
	 * @return
	 */
	public static boolean equalsIgnoreCase(String stringA, String stringB) {

		return StringUtils.equalsIgnoreCase(stringA, stringB);
	}

	/**
	 * Verifica se as duas strings informadas são iguais.
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
