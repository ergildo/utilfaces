/**
 * 
 */
package br.org.ergildo.utilfaces.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author ergildo.dias
 * 
 */
public class ExceptionHandlerFactoryImpl extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	/**
	 * @param parent
	 */
	public ExceptionHandlerFactoryImpl(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new ExceptionHandlerImpl(parent.getExceptionHandler());
	}
}
