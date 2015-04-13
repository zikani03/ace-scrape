package io.github.zikani03.acescrape.data;

import java.util.Date;

public class Contract {
	private Date contractDate;
	private String contractReference;
	private String commodityName;
	private String variety;
	private String grade;
	private double volumeMetricTonnes;
	private double valueUOM;
	private String inco;
	public Contract(Date contractDate, String contractReference,
			String commodityName, String variety, String grade,
			double volumeMetricTonnes, double valueUOM, String inco) {
		super();
		this.contractDate = contractDate;
		this.contractReference = contractReference;
		this.commodityName = commodityName;
		this.variety = variety;
		this.grade = grade;
		this.volumeMetricTonnes = volumeMetricTonnes;
		this.valueUOM = valueUOM;
		this.inco = inco;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getContractReference() {
		return contractReference;
	}
	public void setContractReference(String contractReference) {
		this.contractReference = contractReference;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public double getVolumeMetricTonnes() {
		return volumeMetricTonnes;
	}
	public void setVolumeMetricTonnes(double volumeMetricTonnes) {
		this.volumeMetricTonnes = volumeMetricTonnes;
	}
	public double getValueUOM() {
		return valueUOM;
	}
	public void setValueUOM(double valueUOM) {
		this.valueUOM = valueUOM;
	}
	public String getInco() {
		return inco;
	}
	public void setInco(String inco) {
		this.inco = inco;
	}
	@Override
	public String toString() {
		return "Contract [contractDate=" + contractDate
				+ ", contractReference=" + contractReference
				+ ", commodityName=" + commodityName + ", variety=" + variety
				+ ", grade=" + grade + ", volumeMetricTonnes="
				+ volumeMetricTonnes + ", valueUOM=" + valueUOM + ", inco="
				+ inco + "]";
	}
}
