/**
 * 
 */
package br.org.ergildo.utilfaces.component.dialogbutton;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.CSVBuilder;
import org.primefaces.util.HTML;
import org.primefaces.util.WidgetBuilder;

import br.org.ergildo.utilfaces.util.StringUtil;

/**
 * @author ergildo.dias
 * 
 */
public class DialogButtonRenderer extends CoreRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.render.Renderer#decode(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent)
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		String param = component.getClientId(context);
		if (context.getExternalContext().getRequestParameterMap()
				.containsKey(param)) {
			component.queueEvent(new ActionEvent(component));
		}

		decodeBehaviors(context, component);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		DialogButton dialogButton = (DialogButton) component;

		encodeMarkup(context, dialogButton);
		encodeScript(context, dialogButton);
	}

	/**
	 * @param context
	 * @param dialogButton
	 * @throws IOException
	 */
	protected void encodeMarkup(FacesContext context, DialogButton dialogButton)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = dialogButton.getClientId(context);

		writer.startElement("button", null);
		writer.writeAttribute("id", clientId, "id");
		writer.writeAttribute("name", clientId, "name");
		writer.writeAttribute("type", "button", null);
		writer.writeAttribute("class", HTML.BUTTON_TEXT_ICON_LEFT_BUTTON_CLASS,
				null);

		String onclick = getOnClick(dialogButton, clientId);

		if (dialogButton.isConfirmable()) {
			writer.writeAttribute("data-pfconfirmcommand", onclick, null);
			writer.writeAttribute("onclick",
					getConfirmationScript(dialogButton, context), "onclick");
		} else {
			writer.writeAttribute("onclick", onclick, "onclick");
		}

		writer.writeAttribute("onclick", onclick, "onclick");

		// button icon
		String iconClass = HTML.BUTTON_LEFT_ICON_CLASS + " "
				+ dialogButton.getIcon();
		writer.startElement("span", null);
		writer.writeAttribute("class", iconClass, null);
		writer.endElement("span");

		// text
		writer.startElement("span", null);
		writer.writeAttribute("class", HTML.BUTTON_TEXT_CLASS, null);
		writer.writeText(dialogButton.getValue(), "value");
		writer.endElement("span");

		writer.endElement("button");
	}

	/**
	 * @param dialogButton
	 * @param clientId
	 * @return
	 */
	private String getOnClick(DialogButton dialogButton, String clientId) {
		String request = getAjaxRequest(dialogButton, clientId);
		if (dialogButton.isValidateContent()) {
			request = getCSVAjaxRequest(dialogButton, request);
		}
		return request;
	}

	/**
	 * @param dialogButton
	 * @param clientId
	 * @return
	 */
	private String getAjaxRequest(DialogButton dialogButton, String clientId) {
		AjaxRequestBuilder builder = RequestContext.getCurrentInstance()
				.getAjaxRequestBuilder();

		builder.init().source(clientId)
				.process(dialogButton, dialogButton.resolveProcess())
				.update(dialogButton, dialogButton.resolveUpdate())
				.onstart(getBlockScript(dialogButton))
				.oncomplete(getUnBlockScript(dialogButton)).preventDefault();

		return builder.build();
	}

	/**
	 * @param dialogButton
	 * @param request
	 * @return
	 */
	private String getCSVAjaxRequest(DialogButton dialogButton, String request) {
		CSVBuilder csvb = RequestContext.getCurrentInstance().getCSVBuilder();
		return csvb.init().source("this").ajax(true)
				.process(dialogButton, dialogButton.resolveProcess())
				.update(dialogButton, dialogButton.resolveUpdate())
				.command(request).build();

	}

	/**
	 * @param dialogButton
	 * @param context
	 * @return
	 */
	private String getConfirmationScript(DialogButton dialogButton,
			FacesContext context) {
		return StringUtil.concatenar("PrimeFaces.confirm({source:'",
				dialogButton.getClientId(), "',header:'",
				DialogButton.DEFAULT_CONFIRM_HEADER, "',message:'",
				dialogButton.getConfirmMessage(), "',icon:'",
				DialogButton.DEFAULT_CONFIRM_ICON, "'})");
	}

	private String getBlockScript(DialogButton dialogButton) {
		return StringUtil.concatenar("utilfaces.block('", dialogButton
				.getDialog().getClientId(), "')");

	}

	private String getUnBlockScript(DialogButton dialogButton) {
		return StringUtil.concatenar("utilfaces.unBlock('", dialogButton
				.getDialog().getClientId(), "',args)");

	}

	protected void encodeScript(FacesContext context, DialogButton dialogButton)
			throws IOException {
		String clientId = dialogButton.getClientId(context);
		WidgetBuilder wb = getWidgetBuilder(context);
		wb.init("CommandButton", dialogButton.resolveWidgetVar(), clientId);

		encodeClientBehaviors(context, dialogButton);

		wb.finish();
	}
}
