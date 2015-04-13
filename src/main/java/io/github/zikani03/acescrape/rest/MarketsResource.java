package io.github.zikani03.acescrape.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;
import io.github.zikani03.acescrape.Controller;
import io.github.zikani03.acescrape.client.AbstractHttpClient;
import io.github.zikani03.acescrape.client.Client;
import io.github.zikani03.acescrape.data.MarketCollection;
import io.github.zikani03.acescrape.parser.Parser;
import io.github.zikani03.acescrape.util.JsonTransformer;

public class MarketsResource implements Controller {
	protected final AbstractHttpClient<MarketCollection> client = new Client<MarketCollection>();
	private final String dataUrl;
	//private final String MARKETS_URL = "http://localhost/ace-data/market_info.htm";
	private final Parser<MarketCollection> parser;
	
	private MarketCollection marketCollection;
	
	private final Logger LOG = LoggerFactory.getLogger(MarketsResource.class);
	
	public MarketsResource(final Parser<MarketCollection> parser, final String dataUrl) {
		this.parser = parser;
		this.dataUrl = dataUrl;
	}
	
	@Override
	public void registerRoutes() {
		// Load the markets data
		marketCollection = this.loadMarketsData();
	    /**
	     * @api {get} /markets Retrieve latest commodity exchange market data
	     * @apiGroup Market
	     * 
	     * @apiSuccess {Object[]} markets A collection of rural commodity exchange markets
	     */
		Spark.get("/markets", APPLICATION_JSON, (request, response) -> {
			response.type(APPLICATION_JSON_UTF8);
			// we keep trying to load the data if we have not collected the info before
			if (this.marketCollection == null ) {
				this.marketCollection = this.loadMarketsData();
			}
			return this.marketCollection;
		}, new JsonTransformer());
		LOG.info("Registered /markets");
		
	    /**
	     * @api {get} /markets/:name Retrieve a specific commodity exchange market
	     * @apiParam {String} name The market name
	     * @apiGroup Market
	     * 
	     * @apiSuccess {Object} market A rural commodity exchange market
	     * @apiSuccess {String} market.marketName Name of the market
	     * @apiSuccess {String} market.marketCode Code of the market, assigned by ACE
	     * @apiSuccess {String} market.district District of the market
	     * @apiSuccess {String} market.tradeAgent The agent assigned to this market place
	     * @apiSuccess {String} market.contactNumber Contact number of trade agent
	     * @apiSuccess {String} market.region The region of the market
	     */
		Spark.get("/markets/:name", APPLICATION_JSON, (request, response) -> {
			response.type(APPLICATION_JSON_UTF8);
			
			String marketName = request.params("name");
			
			return marketCollection.findByName(marketName).orElse(null);
			
		}, new JsonTransformer());
		LOG.info("Registered /markets/:name");
	}
	
	private MarketCollection loadMarketsData()  {
		return this.parser.parseFromUri(this.dataUrl);
	}
}
