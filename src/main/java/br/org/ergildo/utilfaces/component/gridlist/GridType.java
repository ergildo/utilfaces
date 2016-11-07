package br.org.ergildo.utilfaces.component.gridlist;

/**
 * @author ergildo.dias
 * 
 */
public enum GridType {

	SIMPLE("simple", 2), DOUBLE("double", 4), TRIPLE("triple", 6);

	private static final Double PERCENT_AREA = 100.0;
	private static final Integer COLUMNS_BY_AREA = 12;
	private final String WIDTH = "width:";
	private String type;
	private Integer columns;

	private GridType(String type, Integer columns) {
		this.type = type;
		this.columns = columns;
	}

	/**
	 * @param type
	 * @return
	 */
	public static GridType getGridType(String type) {
		for (GridType gridType : values()) {
			if (gridType.type.equals(type)) {
				return gridType;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public static Double getWidthColumnByArea() {
		return (PERCENT_AREA / COLUMNS_BY_AREA);
	}

	/**
	 * @param columnLabelType
	 * @return
	 */
	public String getInputStyle(ColumnLabelType columnLabelType) {
		return getStyle(getWidth(columnLabelType));
	}

	/**
	 * @param columnLabelType
	 * @return
	 */
	public String getLabelStyle(ColumnLabelType columnLabelType) {

		return getStyle(columnLabelType.getWidth());
	}

	/**
	 * @param columnLabelType
	 * @return
	 */
	private Double getWidth(ColumnLabelType columnLabelType) {

		return (getSpaceAvailable(columnLabelType) / getColumnsByGroup());
	}

	/**
	 * @param columnLabelType
	 * @return
	 */
	private double getSpaceAvailable(ColumnLabelType columnLabelType) {
		return PERCENT_AREA - getSpaceLabel(columnLabelType);
	}

	/**
	 * @param columnLabelType
	 * @return
	 */
	private double getSpaceLabel(ColumnLabelType columnLabelType) {
		return columnLabelType.getWidth() * getColumnsByGroup();
	}

	/**
	 * @param percent
	 * @return
	 */
	private String getStyle(Double percent) {

		return WIDTH.concat(percent.toString()).concat("%;");
	}

	/**
	 * @return
	 */
	public Integer getColumnsByGroup() {
		return (columns / 2);
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
