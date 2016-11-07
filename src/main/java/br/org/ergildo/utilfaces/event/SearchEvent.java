/**
 * 
 */
package br.org.ergildo.utilfaces.event;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

/**
 * @author ergildo.dias
 *
 */
public class SearchEvent extends FacesEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4642686241235354L;

	private String value;

	public SearchEvent(UIComponent component, String value) {
		super(component);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean isAppropriateListener(FacesListener arg0) {
		return false;
	}

	@Override
	public void processListener(FacesListener arg0) {
		throw new UnsupportedOperationException();
	}
}
