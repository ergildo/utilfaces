/**
 * 
 */
package br.org.ergildo.utilfaces.handler;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.event.PhaseId;

import com.sun.faces.util.FacesLogger;

import br.org.ergildo.utilfaces.util.ExcecaoUtil;
import br.org.ergildo.utilfaces.util.FacesContextUtil;
import br.org.ergildo.utilfaces.util.FacesMessageUtil;

/**
 * @author ergildo.dias
 * 
 */
public class ExceptionHandlerImpl extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	private final Logger log = FacesLogger.FACELETS_INCLUDE.getLogger();

	public ExceptionHandlerImpl(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
				.iterator();
		if (events.hasNext()) {
			while (events.hasNext()) {
				ExceptionQueuedEvent event = events.next();
				ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
						.getSource();
				PhaseId phaseId = context.getPhaseId();
				Throwable excecao = getRootCause(context.getException());
				adicionarMensagem(excecao);
				gerarLog(excecao, phaseId);
				events.remove();

			}

		}

		super.handle();
	}

	private void gerarLog(Throwable excecao, PhaseId phaseId) {
		log.log(Level.SEVERE, "PhaseId: ".concat(phaseId.getName()), excecao);
	}

	/**
	 * @param excecao
	 */
	private void adicionarMensagem(Throwable excecao) {
		String mensagem = ExcecaoUtil.getMensagem(excecao);
		FacesMessage message = FacesMessageUtil.getMensagemErro(mensagem);
		FacesContextUtil.adicionarMensagem(message, null);
	}

}
