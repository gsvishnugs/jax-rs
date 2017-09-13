package org.vgs.accountswa.model;

public class ChartDataModel {

	private String label;

	private Double value;

	public ChartDataModel() {

	}
	
	public ChartDataModel(String label, Double value) {
		this.setLabel(label);
		this.setValue(value);
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

}
