/**
 * 
 */
package br.org.ergildo.utilfaces.component.gridlist;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.renderkit.CoreRenderer;

/**
 * @author ergildo.dias
 * 
 */
public class GridListRenderer extends CoreRenderer {

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
		GridList grid = (GridList) component;
		ResponseWriter writer = context.getResponseWriter();
		String clientId = grid.getClientId(context);

		String style = grid.getStyle();

		writer.startElement("div", grid);
		writer.writeAttribute("id", clientId, "id");
		writer.writeAttribute("class", GridList.DEFAULT_STYLE_CLASS,
				"styleClass");
		if (style != null) {
			writer.writeAttribute("style", style, "style");
		}

		encodeRows(context, grid);

		writer.endElement("div");
	}

	/**
	 * @param context
	 * @param grid
	 * @throws IOException
	 */
	private void encodeRows(FacesContext context, GridList grid)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		int columns = grid.getGrid().getColumns();

		Iterator<UIComponent> children = grid.getChildren().iterator();

		int i = 0;
		while (children.hasNext()) {
			UIComponent child = children.next();
			int colIndex = i % columns;

			if (colIndex == 0) {
				writer.startElement("ul", null);
			}

			if (child.isRendered()) {
				encodeColumn(context, grid, writer, child, colIndex);

				if (isLastChild(children)) {
					int emptycolumns = columns - (colIndex + 1);
					if (emptycolumns > 0) {

						while (colIndex < (columns - 1)) {
							colIndex++;
							encodeColumn(context, grid, writer, null, colIndex);
						}
					}
				}

				i++;
				colIndex = i % columns;

			}

			if (colIndex == 0) {

				writer.endElement("ul");
			}
		}
	}

	/**
	 * @param children
	 * @return boolean
	 */
	private boolean isLastChild(Iterator<UIComponent> children) {
		return !children.hasNext();
	}

	/**
	 * @param context
	 * @param grid
	 * @param writer
	 * @param child
	 * @param colIndex
	 * @throws IOException
	 */
	private void encodeColumn(FacesContext context, GridList grid,
			ResponseWriter writer, UIComponent child, int colIndex)
			throws IOException {
		writer.startElement("li", null);

		String style = grid.getColumnStyle(colIndex);
		String styleClass = grid.getColumnClass(colIndex);
		writer.writeAttribute("style", style, null);
		writer.writeAttribute("class", styleClass, null);
		if (child != null) {
			child.encodeAll(context);
		}

		writer.endElement("li");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		// Rendering happens on encodeEnd
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.render.Renderer#getRendersChildren()
	 */
	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
