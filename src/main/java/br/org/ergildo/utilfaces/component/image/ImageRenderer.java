package br.org.ergildo.utilfaces.component.image;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

import br.org.ergildo.utilfaces.core.model.FormatoImagem;
import br.org.ergildo.utilfaces.util.ImagemUtil;

/**
 * @author ergildo.dias
 * 
 */
public class ImageRenderer extends CoreRenderer {

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
		Image image = (Image) component;
		ResponseWriter writer = context.getResponseWriter();
		String clientId = image.getClientId(context);
		writer.startElement("img", null);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("class", "util-image", null);
		writer.writeAttribute("height", image.getHeight(), null);
		writer.writeAttribute("width", image.getWidth(), null);

		String src = ImagemUtil.getStringImagem(FormatoImagem.PNG,
				image.getValue());
		writer.writeAttribute("src", src, null);
	}
}
