/**
 * 
 */
package br.org.ergildo.utilfaces.util;

import javax.faces.application.FacesMessage;

/**
 * @author ergildo.dias
 * 
 */
public class FacesMessageUtil {

	/**
	 * Retorna {@link FacesMessage} com o severity igual
	 * {@link FacesMessage#SEVERITY_INFO}.
	 * 
	 * @param mensagem
	 * @return
	 */
	public static FacesMessage getMensagemInformacao(String mensagem) {
		FacesMessage message = getFacesMessage(mensagem);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		return message;
	}

	/**
	 * Retorna {@link FacesMessage} com o severity igual
	 * {@link FacesMessage#SEVERITY_WARN}.
	 * 
	 * @param mensagem
	 * @return
	 */
	public static FacesMessage getMensagemAlerta(String mensagem) {
		FacesMessage message = getFacesMessage(mensagem);
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		return message;
	}

	/**
	 * Retorna {@link FacesMessage} com o severity igual
	 * {@link FacesMessage#SEVERITY_ERROR}.
	 * 
	 * @param mensagem
	 * @return
	 */
	public static FacesMessage getMensagemErro(String mensagem) {
		FacesMessage message = getFacesMessage(mensagem);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		return message;
	}

	/**
	 * Retorna {@link FacesMessage}.
	 * 
	 * @param mensagem
	 * @return
	 */
	private static FacesMessage getFacesMessage(String mensagem) {
		return new FacesMessage(mensagem);
	}
}
