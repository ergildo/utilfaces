package br.org.ergildo.utilfaces.component.inputsearch;

import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.api.Widget;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.util.ComponentTraversalUtils;

import br.org.ergildo.utilfaces.event.SearchEvent;

/**
 * @author ergildo.dias
 *
 */
@FacesComponent(InputSearch.COMPONENT_TYPE)
@ResourceDependencies(value = { @ResourceDependency(library = "utilfaces/inputsearch", name = "inputsearch.css"),
		@ResourceDependency(library = "utilfaces/inputsearch", name = "inputsearch.js") })
public class InputSearch extends UIInput implements Widget {
	public static final String COMPONENT_TYPE = "br.org.ergildo.utilfaces.component.InputSearch";
	private static final String DEFAULT_RENDERER = "br.org.ergildo.utilfaces.component.InputSearchRenderer";
	public static final String COMPONENT_FAMILY = "br.org.ergildo.utilfaces.component";

	protected enum PropertyKeys {

		value, var, key, text, dialog, searchListener, required, requiredMessage;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());

		}
	}

	public InputSearch() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		if (event instanceof SearchEvent) {
			MethodExpression methodExpression = getSearchListener();
			if (methodExpression != null) {
				FacesContext context = getFacesContext();
				methodExpression.invoke(context.getELContext(), new Object[] { event });
				if (hasDialogAttached()) {
					updateDialog(context);
				}

				context.renderResponse();

			}
		}
	}

	private void updateDialog(FacesContext context) {

		Dialog dialog = getDialogAttached();
		String clientId = dialog.getClientId(context);
		RequestContext.getCurrentInstance().update(clientId);
	}

	public Dialog getDialogAttached() {
		FacesContext context = getFacesContext();
		String dialog = getDialog();
		UIForm form = ComponentTraversalUtils.closestForm(context, this);
		return (Dialog) SearchExpressionFacade.resolveComponent(context, form, dialog);

	}

	public boolean hasDialogAttached() {
		return getDialog() != null;
	}

	public Object getValue() {

		return getStateHelper().eval(PropertyKeys.value);

	}

	public void setValue(Object value) {
		getStateHelper().put(PropertyKeys.value, value);

	}

	public String getVar() {
		return (String) getStateHelper().eval(PropertyKeys.var, null);
	}

	public void setVar(String _value) {
		getStateHelper().put(PropertyKeys.var, _value);
	}

	public String getKey() {
		return (String) getStateHelper().eval(PropertyKeys.key, null);
	}

	public void setKey(String _value) {
		getStateHelper().put(PropertyKeys.key, _value);
	}

	public void setText(String _value) {
		getStateHelper().put(PropertyKeys.text, _value);
	}

	public String getText() {
		return (String) getStateHelper().eval(PropertyKeys.text, null);
	}

	public String getDialog() {
		return (String) getStateHelper().eval(PropertyKeys.dialog, null);
	}

	public void setDialog(String _value) {
		getStateHelper().put(PropertyKeys.dialog, _value);
	}

	public javax.el.MethodExpression getSearchListener() {
		return (javax.el.MethodExpression) getStateHelper().eval(PropertyKeys.searchListener, null);
	}

	public void setSearchListener(javax.el.MethodExpression _searchListener) {
		getStateHelper().put(PropertyKeys.searchListener, _searchListener);
	}

	public boolean isRequired() {

		return (Boolean) getStateHelper().eval(PropertyKeys.required, false);

	}

	public void setRequired(boolean required) {

		getStateHelper().put(PropertyKeys.required, required);

	}

	public String getRequiredMessage() {

		return (String) getStateHelper().eval(PropertyKeys.requiredMessage);

	}

	public void setRequiredMessage(String message) {

		getStateHelper().put(PropertyKeys.requiredMessage, message);

	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public boolean isSearchRequest(FacesContext context) {
		return context.getExternalContext().getRequestParameterMap()
				.containsKey(getClientId(context) + "_inputSearchRequest");
	}

	@Override
	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if (userWidgetVar != null)
			return userWidgetVar;
		else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public String getSubmittedKey() {
		FacesContext context = getFacesContext();
		if (isSearchRequest(context)) {
			return getRequestSearchedKey(context);
		}
		return getKey();

	}

	public String getRequestSearchedKey(FacesContext context) {

		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String clientId = getClientId();
		String paramKey = clientId.concat("_inputSearchRequest");
		return StringUtils.stripToEmpty(params.get(paramKey));
	}
}
