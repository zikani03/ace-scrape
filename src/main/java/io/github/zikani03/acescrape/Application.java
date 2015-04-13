package io.github.zikani03.acescrape;

import java.io.FileReader;
import java.util.Properties;

import io.github.zikani03.acescrape.rest.ContractsResource;
import io.github.zikani03.acescrape.rest.MarketsResource;
import io.github.zikani03.acescrape.parser.ContractInfoParser;
import io.github.zikani03.acescrape.parser.MarketInfoParser;

public class Application extends AbstractApplication {
	
	private Properties appConfig;
	
	public static void main(String[] args) {
		new Application().run(args);
	}
	
	@Override
	public void run(String... args) {
		String filename = "ace-scrape.properties";
		if (args.length > 0 && !args[0].equals("")) {	
			filename = args[0];
		}
		this.loadConfigs(filename);
		this.setStaticAssetsDir("/webapp");
		super.run(args); // go Spark! go!
	}
	
	@Override
	public void configureControllers() {
		// /markets*
		this.addResource(new MarketsResource(
				new MarketInfoParser(), appConfig.get("markets.url").toString()));
		// /contracts*
		this.addResource(new ContractsResource(
				new ContractInfoParser(), appConfig.get("contracts.url").toString()));
	}
	
	/**
	 * Try to read urls from properties file
	 * 
	 * @param configFilename
	 */
	private void loadConfigs(String configFilename) {
		this.appConfig = new Properties();
		
		try {
			appConfig.load(new FileReader(configFilename));
		} catch (Exception e) {
			appConfig.put("markets.url", "http://localhost:4567/ace-data/market-info.html");
			appConfig.put("contracts.url", "http://localhost:4567/ace-data/contract_info.html");
			e.printStackTrace();
		}
	}
}