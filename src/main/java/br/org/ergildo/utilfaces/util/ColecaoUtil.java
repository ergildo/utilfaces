package br.org.ergildo.utilfaces.util;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

/**
 * Classe de apoio a manipula�ao de cole��es.
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
	 * Verifica se a cole��o informada est� vazia.
	 * 
	 * @param colecao
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isVazia(Collection colecao) {

		return CollectionUtils.isEmpty(colecao);

	}

	/**
	 * Verifica se a cole��o informada possui algum elemento.
	 * 
	 * @param colecao
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isDiferenteDeVazio(Collection colecao) {

		return !CollectionUtils.isEmpty(colecao);

	}

	/**
	 * Verifica se as cole��es informadas s�o iguais. Se ambas cole��es
	 * informadas forem nulas, ser�o consideradas iguais.
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
