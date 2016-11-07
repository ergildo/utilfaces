package br.org.ergildo.utilfaces.component.inputsearch;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseId;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

import br.org.ergildo.utilfaces.event.SearchEvent;

/**
 * @author ergildo.dias
 * 
 */
public class InputSearchRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		InputSearch inputSearch = (InputSearch) component;
		if (inputSearch.isSearchRequest(context)) {

			String submittedValue = inputSearch.getRequestSearchedKey(context);
			inputSearch.setSubmittedValue(submittedValue);
			SearchEvent event = new SearchEvent(component, submittedValue);
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			inputSearch.queueEvent(event);
		} else {
			String submittedValue = StringUtils.stripToEmpty(ComponentUtils.getValueToRender(context, inputSearch));
			inputSearch.setSubmittedValue(submittedValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

		InputSearch inputSearch = (InputSearch) component;
		String clientId = inputSearch.getClientId(context);
		ResponseWriter writer = context.getResponseWriter();
		if (inputSearch.isSearchRequest(context)) {
			encodeContent(inputSearch, clientId, writer, context);
		} else {
			endcodMarkup(inputSearch, clientId, writer, context);
			encodeScript(context, inputSearch);
		}
	}

	private void endcodMarkup(InputSearch inputSearch, String clientId, ResponseWriter writer, FacesContext context)
			throws IOException {
		writer.startElement("div", null);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("class", "ui-inputsearch", null);

		encodeContent(inputSearch, clientId, writer, context);

		writer.endElement("div");
	}

	private void encodeContent(InputSearch inputSearch, String clientId, ResponseWriter writer, FacesContext context)
			throws IOException {
		enableVar(inputSearch, context);
		encodeInput(inputSearch, clientId, writer);

		encodeSearch(inputSearch, writer);

		disableVar(inputSearch, context);

	}

	private void enableVar(InputSearch inputSearch, FacesContext context) {
		ValueExpression valueExpression = inputSearch.getValueExpression("value");
		Object value = valueExpression.getValue(context.getELContext());
		String var = inputSearch.getVar();
		Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
		if (var != null) {
			requestMap.put(var, value);
		}
	}

	private void disableVar(InputSearch inputSearch, FacesContext context) {
		String var = inputSearch.getVar();
		Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
		if (var != null) {
			requestMap.remove(var);
		}
	}

	private void encodeSearch(InputSearch inputSearch, ResponseWriter writer) throws IOException {
		writer.startElement("div", null);
		writer.writeAttribute("class",
				"ui-inputsearch-seach ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all", null);

		encodeDescription(inputSearch, writer);

		encodIcon(inputSearch, writer);

		writer.endElement("div");
	}

	private void encodIcon(InputSearch inputSearch, ResponseWriter writer) throws IOException {
		writer.startElement("div", null);
		writer.writeAttribute("class", "ui-inputsearch-seach-icon", null);
		if (inputSearch.hasDialogAttached()) {
			Dialog dialog = inputSearch.getDialogAttached();
			String widgetVar = dialog.resolveWidgetVar();
			String onclick = "PF('" + widgetVar + "').show();";
			writer.writeAttribute("onclick", onclick, null);
		}

		writer.endElement("div");
	}

	private void encodeDescription(InputSearch inputSearch, ResponseWriter writer) throws IOException {
		writer.startElement("div", null);
		writer.writeAttribute("class", "ui-inputsearch-seach-desc", null);
		writer.write(inputSearch.getText());
		writer.endElement("div");
	}

	private void encodeInput(InputSearch inputSearch, String clientId, ResponseWriter writer) throws IOException {

		writer.startElement("input", null);
		writer.writeAttribute("id", clientId + "_input", null);
		writer.writeAttribute("class",
				"ui-inputsearch-input ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all", null);
		writer.writeAttribute("text", "text", null);
		writer.writeAttribute("value", inputSearch.getKey(), null);
		writer.endElement("input");
	}

	protected void encodeScript(FacesContext context, InputSearch inputSearch) throws IOException {
		String clientId = inputSearch.getClientId(context);
		WidgetBuilder wb = getWidgetBuilder(context);
		wb.init("InputSearch", inputSearch.resolveWidgetVar(), clientId).finish();

	}

}
