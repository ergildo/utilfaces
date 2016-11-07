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
	 * Verifica se a exce�ao informada � uma exce��o tratada pela a aplica��o,
	 * caso seja, retorna a mensagem referente a exce��o informada, do contr�rio
	 * retorna uma mensagem de falha n�o prevista.
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
	 * Verifica se a exce��o informada � uma exce��o tratada pela a aplica��o,
	 * caso seja retorna a mesma, do contr�rio obt�m as causas e verifica dentre
	 * elas se existe alguma sendo tratada pela a aplica��o, caso exista,
	 * retorna a primeira causa tratada pela a aplica��o, do contr�rio retorna a
	 * exce��o original, ou seja a primeira da pilha.
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
	 * Verifica se a exce��o informada � a exce��o original, ou seja a primeira
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
	 * Retorna a mensagem de falha n�o prevista.
	 * 
	 * @return
	 */
	private static String getMensagemFalhaNaoPrevista() {
		return ResourceUtil.getMensagem(Constantes.APP_BASE_FALHA_NAO_PREVISTA);
	}

	/**
	 * Retorna a mensagem referente a exce��o informada.
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
	 * Retorna o nome da exce��o informada.
	 * 
	 * @param e
	 * @return
	 */
	private static String getNomeExcecao(Throwable e) {
		return e.getClass().getCanonicalName();
	}
}
