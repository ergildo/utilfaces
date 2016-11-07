package br.org.ergildo.utilfaces.component.dialoghandlerbehavior;

import java.util.Collection;

import javax.faces.component.ActionSource;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.render.ClientBehaviorRenderer;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.api.ClientBehaviorRenderingMode;
import org.primefaces.context.RequestContext;
import org.primefaces.util.AjaxRequestBuilder;

/**
 * @author ergildo.dias
 * 
 */
public class DialogHandlerBehaviorRenderer extends ClientBehaviorRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component, ClientBehavior behavior) {
		DialogHandlerBehavior ajaxBehavior = (DialogHandlerBehavior) behavior;

		if (!ajaxBehavior.isDisabled()) {
			AjaxBehaviorEvent event = new AjaxBehaviorEvent(component, behavior);

			PhaseId phaseId = getPhaseId(component, ajaxBehavior);

			event.setPhaseId(phaseId);

			component.queueEvent(event);
		}
	}

	/**
	 * @param component
	 * @param ajaxBehavior
	 * @return
	 */
	private PhaseId getPhaseId(UIComponent component, DialogHandlerBehavior ajaxBehavior) {
		return isImmediate(component, ajaxBehavior) ? PhaseId.APPLY_REQUEST_VALUES : PhaseId.INVOKE_APPLICATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.render.ClientBehaviorRenderer#getScript(javax.faces.component
	 * .behavior.ClientBehaviorContext,
	 * javax.faces.component.behavior.ClientBehavior)
	 */
	@Override
	public String getScript(ClientBehaviorContext behaviorContext, ClientBehavior behavior) {
		DialogHandlerBehavior ajaxBehavior = (DialogHandlerBehavior) behavior;
		if (ajaxBehavior.isDisabled()) {
			return null;
		}

		UIComponent component = behaviorContext.getComponent();

		ClientBehaviorRenderingMode renderingMode = ClientBehaviorRenderingMode.OBSTRUSIVE;

		Collection<ClientBehaviorContext.Parameter> behaviorParameters = behaviorContext.getParameters();
		if (behaviorParameters != null && !behaviorParameters.isEmpty()) {
			for (ClientBehaviorContext.Parameter behaviorParameter : behaviorParameters) {
				if (behaviorParameter.getValue() != null
						&& behaviorParameter.getValue() instanceof ClientBehaviorRenderingMode) {
					renderingMode = (ClientBehaviorRenderingMode) behaviorParameter.getValue();
					break;
				}
			}
		}

		String source = behaviorContext.getSourceId();
		String process = ajaxBehavior.getProcess();
		String update = ajaxBehavior.getExpressionFor();

		AjaxRequestBuilder builder = RequestContext.getCurrentInstance().getAjaxRequestBuilder();

		String request = builder.init().source(source).event(behaviorContext.getEventName()).process(component, process)
				.update(component, update).async(ajaxBehavior.isAsync()).global(ajaxBehavior.isGlobal())
				.delay(ajaxBehavior.getDelay()).timeout(ajaxBehavior.getTimeout())
				.partialSubmit(ajaxBehavior.isPartialSubmit(), ajaxBehavior.isPartialSubmitSet(),
						ajaxBehavior.getPartialSubmitFilter())
				.resetValues(ajaxBehavior.isResetValues(), ajaxBehavior.isResetValuesSet())
				.ignoreAutoUpdate(ajaxBehavior.isIgnoreAutoUpdate()).onstart(ajaxBehavior.getOnstart())
				.onerror(ajaxBehavior.getOnerror()).onsuccess(ajaxBehavior.getOnsuccess())
				.oncomplete(ajaxBehavior.getOncomplete()).params(component).buildBehavior(renderingMode);

		return request;
	}

	/**
	 * @param component
	 * @param ajaxBehavior
	 * @return
	 */
	private boolean isImmediate(UIComponent component, AjaxBehavior ajaxBehavior) {
		boolean immediate = false;

		if (ajaxBehavior.isImmediateSet()) {
			immediate = ajaxBehavior.isImmediate();
		} else if (component instanceof EditableValueHolder) {
			immediate = ((EditableValueHolder) component).isImmediate();
		} else if (component instanceof ActionSource) {
			immediate = ((ActionSource) component).isImmediate();
		}

		return immediate;
	}

}
