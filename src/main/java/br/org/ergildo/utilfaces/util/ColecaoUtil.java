package br.org.ergildo.utilfaces.util;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

/**
 * Classe de apoio a manipulaçao de coleções.
 * 
 * @author ergildo.dias
 * 
 */
public final class ColecaoUtil {

	/**
	 * 
	 */
	private ColecaoUtil() {
	}

	/**
	 * Verifica se a coleção informada está vazia.
	 * 
	 * @param colecao
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isVazia(Collection colecao) {

		return CollectionUtils.isEmpty(colecao);

	}

	/**
	 * Verifica se a coleção informada possui algum elemento.
	 * 
	 * @param colecao
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isDiferenteDeVazio(Collection colecao) {

		return !CollectionUtils.isEmpty(colecao);

	}

	/**
	 * Verifica se as coleções informadas são iguais. Se ambas coleções
	 * informadas forem nulas, serão consideradas iguais.
	 * 
	 * @param colecaoA
	 * @param colecaoB
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isColecoesIguais(Collection colecaoA,
			Collection colecaoB) {

		if (colecaoA == null && colecaoB == null) {
			return true;
		}

		if (colecaoA != null && colecaoB != null) {
			return CollectionUtils.isEqualCollection(colecaoA, colecaoB);
		}

		return false;
	}
}
