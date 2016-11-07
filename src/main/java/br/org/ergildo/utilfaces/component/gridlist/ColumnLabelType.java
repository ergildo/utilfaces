/**
 * 
 */
package br.org.ergildo.utilfaces.component.gridlist;

/**
 * @author ergildo.dias
 * 
 */
public enum ColumnLabelType {
	SMALL("small", 1), MEDIUM("medium", 2), LARGE("large", 3);
	private String type;
	private Integer columns;

	/**
	 * @param type
	 * @param columns
	 */
	private ColumnLabelType(String type, Integer columns) {
		this.type = type;
		this.columns = columns;
	}

	/**
	 * @param type
	 * @return
	 */
	public static ColumnLabelType getColumnLabelType(String type) {
		for (ColumnLabelType columnLabelType : values()) {
			if (columnLabelType.type.equals(type)) {
				return columnLabelType;
			}
		}
		return null;
	}

	/**
	 * @return the width
	 */
	public Double getWidth() {
		return GridType.getWidthColumnByArea() * columns;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the columns
	 */
	public Integer getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Integer columns) {
		this.columns = columns;
	}

}
