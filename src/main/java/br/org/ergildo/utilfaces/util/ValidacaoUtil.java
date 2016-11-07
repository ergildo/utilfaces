package br.org.ergildo.utilfaces.util;

import java.util.Collection;

import org.apache.commons.lang.Validate;

/**
 * Classe de apoio a validação de valores/argumentos.
 * 
 * 
 * @author ergildo.dias
 * 
 */
public final class ValidacaoUtil {

	/**
	 * 
	 */
	private ValidacaoUtil() {
	}

	/**
	 * Valida o objeto verificando o valor do mesmo. Caso seja nulo, lançará
	 * {@link IllegalArgumentException}
	 * 
	 * @param objeto
	 */
	public static void notNull(Object objeto) {
		Validate.notNull(objeto);
	}

	/**
	 * Valida o objeto verificando o valor do mesmo. Caso seja nulo, lançará
	 * {@link IllegalArgumentException} com a mensagem informada.
	 * 
	 * @param objeto
	 * @param mensagem
	 */
	public static void notNull(Object objeto, String mensagem) {
		Validate.notNull(objeto, mensagem);
	}

	/**
	 * Valida a coleção informada, verificando se a mesma é nula ou vazia.
	 * 
	 * @param colecao
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Collection collection) {
		Validate.notEmpty(collection);
	}

}