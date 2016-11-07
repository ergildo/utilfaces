/**
 * 
 */
package br.org.ergildo.utilfaces.event;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import org.primefaces.context.RequestContext;

import br.org.ergildo.utilfaces.util.FacesContextUtil;

/**
 * @author ergildo.dias
 * 
 */
public class InputTextPostValidateEvent implements SystemEventListener {
	private final String FALIED_COMPONENT = "faliedComponent";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.SystemEventListener#processEvent(javax.faces.event.
	 * SystemEvent)
	 */

	public void processEvent(SystemEvent event) throws AbortProcessingException {

		if (FacesContextUtil.getFacesContext().isValidationFailed() && hasFaliedComponent()) {
			String faliedComponent = findFirstFaliedComponent();

			getRequestContext().addCallbackParam(FALIED_COMPONENT, faliedComponent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.SystemEventListener#isListenerForSource(java.lang.
	 * Object )
	 */

	public boolean isListenerForSource(Object source) {
		return true;
	}

	/**
	 * @return
	 */
	private String findFirstFaliedComponent() {
		FacesContext context = FacesContextUtil.getFacesContext();
		for (Iterator<String> iterator = context.getClientIdsWithMessages(); iterator.hasNext();) {
			String clientId = iterator.next();
			return clientId;
		}

		return null;
	}

	/**
	 * @return
	 */
	private RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	/**
	 * @return
	 */
	private boolean hasFaliedComponent() {
		return getRequestContext().getCallbackParams().get(FALIED_COMPONENT) == null;
	}

}
