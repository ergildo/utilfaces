package br.org.ergildo.utilfaces.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import br.org.ergildo.utilfaces.constantes.Constantes;

/**
 * @author ergildo.dias
 * 
 */
public class ExcecaoUtil {
	/**
	 * Verifica se a exceçao informada é uma exceção tratada pela a aplicação,
	 * caso seja, retorna a mensagem referente a exceção informada, do contrário
	 * retorna uma mensagem de falha não prevista.
	 * 
	 * @param e
	 * @return
	 */
	public static String getMensagem(final Throwable e) {

		try {
			Throwable excecaoTratada = getExcecaoTratada(e);
			String chaveMensagem = getNomeExcecao(excecaoTratada);
			return getMensagemPorNomeExcecao(chaveMensagem);
		} catch (MissingResourceException exception) {

			return getMensagemFalhaNaoPrevista();
		}

	}

	/**
	 * Verifica se a exceção informada é uma exceção tratada pela a aplicação,
	 * caso seja retorna a mesma, do contrário obtém as causas e verifica dentre
	 * elas se existe alguma sendo tratada pela a aplicação, caso exista,
	 * retorna a primeira causa tratada pela a aplicação, do contrário retorna a
	 * exceção original, ou seja a primeira da pilha.
	 * 
	 * @param e
	 * @return excecao
	 */
	private static Throwable getExcecaoTratada(final Throwable e) {
		Throwable excecao = e;
		ResourceBundle resourceBundle = ResourceUtil.getResourceBundle();

		while (!isExcecaoOriginal(excecao)) {
			if (resourceBundle.containsKey(getNomeExcecao(excecao))) {
				break;
			}
			excecao = excecao.getCause();
		}
		return excecao;
	}

	/**
	 * Verifica se a exceção informada é a exceção original, ou seja a primeira
	 * da pilha.
	 * 
	 * @param excecao
	 * @return
	 */
	private static boolean isExcecaoOriginal(final Throwable excecao) {
		return excecao.getCause() == null
				|| excecao.getClass() == excecao.getCause().getClass();
	}

	/**
	 * Retorna a mensagem de falha não prevista.
	 * 
	 * @return
	 */
	private static String getMensagemFalhaNaoPrevista() {
		return ResourceUtil.getMensagem(Constantes.APP_BASE_FALHA_NAO_PREVISTA);
	}

	/**
	 * Retorna a mensagem referente a exceção informada.
	 * 
	 * @param nomeExcecao
	 * @return
	 * @throws MissingResourceException
	 */
	private static String getMensagemPorNomeExcecao(String nomeExcecao)
			throws MissingResourceException {
		return ResourceUtil.getMensagem(nomeExcecao);
	}

	/**
	 * Retorna o nome da exceção informada.
	 * 
	 * @param e
	 * @return
	 */
	private static String getNomeExcecao(Throwable e) {
		return e.getClass().getCanonicalName();
	}
}
