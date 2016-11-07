/*
 * UtilMensagem.java
 * 
 * Classe com metodos utilitarios para acessar arquivos de mensagens.
 * 
 * 
 */

package br.org.ergildo.utilfaces.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import br.org.ergildo.utilfaces.constantes.Constantes;

/**
 * Classe com metodos utilitarios para acessar arquivos de mensagens.
 * 
 * @author ergildo.dias
 * 
 */
public final class ResourceUtil {

	/**
	 * Retorna o {@link ResourceBundle} associado ao caminho do arquivo de
	 * mensagens da aplicacao especificado.
	 * 
	 * @param caminhoArquivoMensagens
	 * @return
	 */
	private static ResourceBundle getResourceBundle(
			String caminhoArquivoMensagens) {
		return ResourceBundle.getBundle(caminhoArquivoMensagens);
	}

	/**
	 * Retorna o {@link ResourceBundle} associado ao arquivo de mensagens
	 * especificado no faces-config.xml.
	 * 
	 * @return
	 */
	public static ResourceBundle getResourceBundle() {
		if (FacesContextUtil.getFacesContext() != null) {
			return FacesContextUtil.getResourceBundle();
		}
		return getResourceBundle(Constantes.RESOURCE_BUNDLE);
	}

	/**
	 * Retorna a mensagem do arquivo de mensagens especificado, identificada
	 * pela chave informada. A mensagem retornada e formatada a partir dos
	 * parametros especificados.
	 * 
	 * @param caminhoArquivoMensagens
	 * @param chave
	 * @param parametros
	 * @return
	 */
	public static String getMensagem(String caminhoArquivoMensagens,
			String chave, Object... parametros) {
		ResourceBundle resourceBundle = getResourceBundle(caminhoArquivoMensagens);
		return getMensagem(chave, resourceBundle, parametros);
	}

	/**
	 * @param chave
	 * @param resourceBundle
	 * @param parametros
	 * @return
	 */
	private static String getMensagem(String chave,
			ResourceBundle resourceBundle, Object... parametros) {
		String mensagem = null;
		if (resourceBundle != null) {
			mensagem = resourceBundle.getString(chave);
		}
		if (parametros != null) {
			mensagem = MessageFormat.format(mensagem, parametros);
		}
		return mensagem;
	}

	/**
	 * Retorna a mensagem do arquivo de mensagens especificado no
	 * faces-config.xml, identificada pela chave informada. A mensagem retornada
	 * e formatada a partir dos parametros especificados.
	 * 
	 * @param chave
	 * @param parametros
	 * @return
	 */
	public static String getMensagem(String chave, Object... parametros) {

		ResourceBundle resourceBundle = getResourceBundle();

		return getMensagem(chave, resourceBundle, parametros);

	}
}