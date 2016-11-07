/**
 * 
 */
package br.org.ergildo.utilfaces.component.gridlist;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIPanel;

/**
 * @author ergildo.dias
 * 
 */
@ResourceDependencies({ @ResourceDependency(library = "utilfaces", name = "gridList/gridlist.css") })
public class GridList extends UIPanel {
	public static final String COMPONENT_TYPE = "br.org.ergildo.utilfaces.component.GridList";
	public static final String COMPONENT_FAMILY = "br.org.ergildo.utilfaces.component";
	private static final String DEFAULT_RENDERER = "br.org.ergildo.utilfaces.component.GridListRenderer";
	public final static String DEFAULT_STYLE_CLASS = "util-grid-list";
	public final static String DEFAULT_LABEL_CLASS = "util-output-label";
	public final static String DEFAULT_TEXT_CLASS = "util-input-text";

	protected enum PropertyKeys {

		style, styleClass, gridType, columnLabelType, labelClass, textClass;

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

	/**
	 * 
	 */
	public GridList() {
		setRendererType(DEFAULT_RENDERER);
	}

	/**
	 * @param colIndex
	 * @return
	 */
	public String getColumnStyle(int colIndex) {
		ColumnLabelType columnLabelType = getColumnLabel();
		return isColumnLabel(colIndex) ? getGrid().getLabelStyle(
				columnLabelType) : getGrid().getInputStyle(columnLabelType);
	}

	/**
	 * @param colIndex
	 * @return
	 */
	public String getColumnClass(int colIndex) {
		return isColumnLabel(colIndex) ? getLabelClass() : getTextClass();
	}

	/**
	 * @param colIndex
	 * @return boolean
	 */
	private boolean isColumnLabel(int colIndex) {
		return colIndex % 2 == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.component.UIPanel#getFamily()
	 */
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * @return style
	 */
	public java.lang.String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style,
				null);
	}

	/**
	 * @param _style
	 */
	public void setStyle(java.lang.String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * @return styleClass
	 */
	public java.lang.String getStyleClass() {
		return (java.lang.String) getStateHelper().eval(
				PropertyKeys.styleClass, null);
	}

	/**
	 * @param _styleClass
	 */
	public void setStyleClass(java.lang.String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * @return gridType
	 */
	public GridType getGrid() {
		String type = (java.lang.String) getStateHelper().eval(
				PropertyKeys.gridType, GridType.SIMPLE.getType());
		return GridType.getGridType(type);
	}

	/**
	 * @param _gridType
	 */
	public void setGridType(java.lang.String _gridType) {
		getStateHelper().put(PropertyKeys.gridType, _gridType);
	}

	/**
	 * @return columnLabelType
	 */
	public ColumnLabelType getColumnLabel() {
		String type = (java.lang.String) getStateHelper().eval(
				PropertyKeys.columnLabelType, ColumnLabelType.MEDIUM.getType());
		return ColumnLabelType.getColumnLabelType(type);
	}

	/**
	 * @param _columnLabelType
	 */
	public void setColumnLabelType(java.lang.String _columnLabelType) {
		getStateHelper().put(PropertyKeys.columnLabelType, _columnLabelType);
	}

	/**
	 * @return styleClass
	 */
	public java.lang.String getLabelClass() {
		return (java.lang.String) getStateHelper().eval(
				PropertyKeys.labelClass, DEFAULT_LABEL_CLASS);
	}

	/**
	 * @param _labelClass
	 */
	public void setLabelClass(java.lang.String _labelClass) {
		getStateHelper().put(PropertyKeys.labelClass, _labelClass);
	}

	/**
	 * @return textClass
	 */
	public java.lang.String getTextClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.textClass,
				DEFAULT_TEXT_CLASS);
	}

	/**
	 * @param _textClass
	 */
	public void setTextClass(java.lang.String _textClass) {
		getStateHelper().put(PropertyKeys.textClass, _textClass);
	}

}
