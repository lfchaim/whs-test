package br.com.whs.whstest;

public class Column {
	
	private String columnName;
	private Integer dataType;
	private Integer columnSize;
	private Integer decimalDigits;
	private Integer ordinalPosition;
	private Boolean isAutoincrement;

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Integer getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}
	public Integer getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	public Boolean getIsAutoincrement() {
		return isAutoincrement;
	}
	public void setIsAutoincrement(Boolean isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}
	
}
