/**
 * 
 */
package br.org.ergildo.utilfaces.component.dialogbutton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.visit.VisitContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.primefaces.component.api.Widget;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.util.ComponentUtils;
import org.primefaces.visit.ResetInputVisitCallback;

import br.org.ergildo.utilfaces.util.FacesContextUtil;

/**
 * @author ergildo.dias
 * 
 */
@ResourceDependencies({ @ResourceDependency(library = "primefaces", name = "primefaces.css"),
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "jquery/jquery-plugins.js"),
		@ResourceDependency(library = "utilfaces", name = "jquery/jquery-block.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "utilfaces", name = "core/js/util-faces.js") })
public class DialogButton extends UICommand implements Widget, ClientBehaviorHolder {
	public static final String COMPONENT_TYPE = "br.org.ergildo.utilfaces.component.DialogButton";
	public static final String COMPONENT_FAMILY = "br.org.ergildo.utilfaces.component";
	public static final String DEFAULT_CONFIRM_HEADER = "Confirmação";
	public static final String DEFAULT_CONFIRM_ICON = "ui-icon-alert";

	private final String DEFAULT_RENDERER = "br.org.ergildo.utilfaces.component.DialogButtonRenderer";
	private final String DEFAULT_UPDATE = "@parent";
	private final String LOADER_ICON = "core/images/ajax-loader.gif";

	protected enum PropertyKeys {
		widgetVar, validateContent, closeOnComplete, update, icon, confirmMessage, addFrom, addTo;
	}

	public DialogButton() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		super.broadcast(event);
		if (isCloseOnComplete() && !FacesContextUtil.hasError()) {
			resetInputValues();
			update();
			closeDialog();
		}
	}

	private void resetInputValues() {
		VisitContext visitContext = VisitContext.createVisitContext(FacesContextUtil.getFacesContext(), null,
				ComponentUtils.VISIT_HINTS_SKIP_UNRENDERED);
		getDialog().visitTree(visitContext, ResetInputVisitCallback.INSTANCE);
	}

	private void update() {
		updateValueListener();
		String update = getUpdate();
		if (update != null) {
			String resolvedExpressions = SearchExpressionFacade.resolveClientId(getFacesContext(), this, update);
			RequestContext.getCurrentInstance().update(resolvedExpressions);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateValueListener() {
		ValueExpression value = getValueExpression(PropertyKeys.addFrom.toString());

		ValueExpression valueListener = getValueExpression(PropertyKeys.addTo.toString());
		if (value == null || valueListener == null) {
			return;
		}

		Class<?> type = valueListener.getType(getELContext());
		Object newValue = value.getValue(getELContext());

		if (List.class.isAssignableFrom(type)) {
			List values = (List<?>) valueListener.getValue(getELContext());
			if (values == null) {
				values = new ArrayList();
			}
			if (!values.contains(newValue)) {
				values.add(newValue);
				valueListener.setValue(getELContext(), values);
			}

		} else {
			valueListener.setValue(getELContext(), newValue);

		}
	}

	private ELContext getELContext() {
		return getFacesContext().getELContext();
	}

	private void closeDialog() {
		String dialog = getDialog().resolveWidgetVar();

		String script = "PF('".concat(dialog).concat("').hide();");

		RequestContext.getCurrentInstance().execute(script);

	}

	private String addChildrenDialog(String expression) {
		for (UIComponent component : getDialog().getChildren()) {
			expression = expression.concat(", ").concat(component.getClientId(getFacesContext()));
		}
		return expression;
	}

	public Dialog getDialog() {
		UIComponent parent = getParent();

		while (parent != null) {
			if (parent instanceof Dialog) {
				return (Dialog) parent;
			}

			parent = parent.getParent();
		}
		String message = "O componente ".concat(getClientId(getFacesContext())).concat(" do tipo ")
				.concat(COMPONENT_TYPE).concat(" deve estar dentro de um dialog");
		throw new FacesException(message);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getLoaderIcon() {
		return FacesContextUtil.getRequestPath(LOADER_ICON);
	}

	public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}

	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

	public boolean isValidateContent() {
		return (Boolean) getStateHelper().eval(PropertyKeys.validateContent, true);
	}

	public void setValidateContent(boolean _validateContent) {
		getStateHelper().put(PropertyKeys.validateContent, _validateContent);
	}

	public boolean isCloseOnComplete() {
		return (Boolean) getStateHelper().eval(PropertyKeys.closeOnComplete, false);
	}

	public void setCloseOnComplete(boolean _closeOnComplete) {
		getStateHelper().put(PropertyKeys.closeOnComplete, _closeOnComplete);
	}

	public String resolveProcess() {
		String process = getClientId();
		if (isValidateContent()) {
			return addChildrenDialog(process);
		}
		return process;
	}

	public String resolveUpdate() {

		return addChildrenDialog(DEFAULT_UPDATE);
	}

	public java.lang.String getUpdate() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.update, null);
	}

	public void setUpdate(java.lang.String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	public java.lang.String getIcon() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.icon, null);
	}

	public void setIcon(java.lang.String _icon) {
		getStateHelper().put(PropertyKeys.icon, _icon);
	}

	public java.lang.String getConfirmMessage() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.confirmMessage, null);
	}

	public void setConfirmMessage(java.lang.String _confirmMessage) {
		getStateHelper().put(PropertyKeys.confirmMessage, _confirmMessage);
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click"));

	@Override
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	@Override
	public String getDefaultEventName() {
		return "click";
	}

	public boolean isConfirmable() {
		return getConfirmMessage() != null;
	}

	public String resolveWidgetVar() {
		return ComponentUtils.resolveWidgetVar(getFacesContext(), this);
	}
}
