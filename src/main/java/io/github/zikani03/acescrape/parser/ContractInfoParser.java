package io.github.zikani03.acescrape.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.github.zikani03.acescrape.data.Contract;
import io.github.zikani03.acescrape.data.ContractsCollection;

public class ContractInfoParser extends AbstractInfoParser<ContractsCollection> {
	private final String tableId = "ContentPlaceHolderDefault_BaseMasterContent_ctl00_DealsReport_4_gvContractlist";
	
	private static final int CONTRACT_DATE = 0;
	private static final int CONTRACT_REFERENCE = 1;
	private static final int COMMODITY_NAME = 2;
	private static final int VARIETY = 3;
	private static final int GRADE = 4;
	private static final int VOLUME_METRIC_TONNES = 5;
	private static final int VALUE_UOM = 6;
	private static final int INCO = 7;
    
    private final ContractsCollection contracts = new ContractsCollection();
    
    @Override
	protected ContractsCollection parseToCollection() {
		StringBuilder sb = new StringBuilder();
		
		// Build selector
		String baseSelector = sb.append("#").append(this.tableId).append(" ").toString();
		// #id > tr.
		// Get the elements with class .DetailsViewRow
		
		Elements elements = document.select(baseSelector.concat(" tr.DetailsviewRow"));

		for(Element tr: elements) {
			this.contracts.addContract(createMarketFromElement(tr));
		}
		
		// Get the elements with class .Alternating
		elements = document.select(baseSelector.concat(" tr.Alternating"));

		for(Element tr: elements) {
			this.contracts.addContract(createMarketFromElement(tr));
		}
		
		return contracts;
	}
	
	protected Contract createMarketFromElement(Element tr) {

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date contractDate = new Date();
		try {
			contractDate = df.parse(tr.child(CONTRACT_DATE).text());
		} catch (ParseException e) {
			LOG.warn("Failed to parse contract date");
			LOG.warn(e.getMessage());
		}
		String volStr = tr.child(VOLUME_METRIC_TONNES).text().toUpperCase(Locale.ENGLISH);
		// the value of the volume is read in the following format NUMBER "MT" e.g. 90.000 MT
		volStr = volStr.substring(0, volStr.indexOf("MT") != -1 ? volStr.indexOf("MT") : 0);
		
		double volume = Double.parseDouble(volStr.equals("") ? "0.0" : volStr);
		double valueUoM = Double.parseDouble(tr.child(VALUE_UOM).text());
		return new Contract(
				contractDate,
				tr.child(CONTRACT_REFERENCE).text(),
				tr.child(COMMODITY_NAME).text(),
				tr.child(VARIETY).text(),
				tr.child(GRADE).text(),
				volume,
				valueUoM,
				tr.child(INCO).text());
	}
}
