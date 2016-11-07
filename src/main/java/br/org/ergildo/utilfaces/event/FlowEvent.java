/**
 * 
 */
package br.org.ergildo.utilfaces.event;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

import br.org.ergildo.utilfaces.enums.FlowEventType;


public class FlowEvent extends FacesEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4642686241235354L;
	private String currentStep;
	private String nexStep;
	private FlowEventType eventType;

	/**
	 * @param component
	 * @param currentStep
	 * @param nexStep
	 * @param eventType
	 */
	public FlowEvent(UIComponent component, String currentStep, String nexStep,
			FlowEventType eventType) {
		super(component);
		this.currentStep = currentStep;
		this.nexStep = nexStep;
		this.eventType = eventType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.FacesEvent#isAppropriateListener(javax.faces.event.
	 * FacesListener)
	 */
	@Override
	public boolean isAppropriateListener(FacesListener listener) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.FacesEvent#processListener(javax.faces.event.FacesListener
	 * )
	 */
	@Override
	public void processListener(FacesListener listener) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return 
	 */
	public Object[] getParams() {
		return isFinish() ? new Object[] {} : new Object[] { this };
	}

	/**
	 * @return boolean
	 */
	public boolean isBack() {
		return FlowEventType.back.equals(eventType);
	}

	/**
	 * @return boolean
	 */
	public boolean isNext() {
		return FlowEventType.next.equals(eventType);
	}

	/**
	 * @return boolean
	 */
	public boolean isFinish() {
		return FlowEventType.finish.equals(eventType);
	}

	/**
	 * @return the currentStep
	 */
	public String getCurrentStep() {
		return currentStep;
	}

	/**
	 * @return the nexStep
	 */
	public String getNexStep() {
		return nexStep;
	}

	/**
	 * @return the eventType
	 */
	public FlowEventType getEventType() {
		return eventType;
	}

}
