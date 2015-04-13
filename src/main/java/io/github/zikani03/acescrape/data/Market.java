package io.github.zikani03.acescrape.data;

public class Market {

	private String marketName;
	private String marketCode;
	private String district;
	private String region;
	private String tradeAgent;
	private String contactNumber;
	
	
	public Market(String marketName, String marketCode, String district,
			String region, String tradeAgent, String contactNumber) {
		super();
		this.marketName = marketName;
		this.marketCode = marketCode;
		this.district = district;
		this.region = region;
		this.tradeAgent = tradeAgent;
		this.contactNumber = contactNumber;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTradeAgent() {
		return tradeAgent;
	}
	public void setTradeAgent(String tradeAgent) {
		this.tradeAgent = tradeAgent;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Override
	public String toString() {
		return "Market [marketName=" + marketName + ", marketCode="
				+ marketCode + ", district=" + district + ", region=" + region
				+ ", tradeAgent=" + tradeAgent + ", contactNumber="
				+ contactNumber + "]";
	}
	
	
}
