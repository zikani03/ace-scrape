package io.github.zikani03.acescrape.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;
import io.github.zikani03.acescrape.Controller;
import io.github.zikani03.acescrape.data.ContractsCollection;
import io.github.zikani03.acescrape.parser.Parser;
import io.github.zikani03.acescrape.util.JsonTransformer;

public class ContractsResource implements Controller {
	private final Logger LOG = LoggerFactory.getLogger(ContractsResource.class);
	
	private final String dataUrl;
	private final Parser<ContractsCollection> parser;
	private ContractsCollection contracts;
	
	public ContractsResource(final Parser<ContractsCollection> parser, final String dataUrl) {
		this.parser = parser;
		this.dataUrl =  dataUrl;
	}
	
	@Override
	public void registerRoutes() {
		// Load the markets data
		contracts = this.loadData();
	    /**
	     * @api {get} /contracts Retrieve latest commodity exchange contract data
	     * @apiGroup Contract
	     * 
	     * @apiSuccess {Object[]} contracts A collection of commodity exchange contracts
	     */
		Spark.get("/contracts", APPLICATION_JSON, (request, response) -> {
			response.type(APPLICATION_JSON_UTF8);
			// we keep trying to load the data if we have not collected the info before
			if (this.contracts == null ) {
				this.contracts = this.loadData();
			}
			return this.contracts;
		}, new JsonTransformer());
		LOG.info("Registered /contracts");
		
	    /**
	     * @api {get} /contracts/:year/:id Retrieve a specific commodity exchange contract item
	     * @apiParam {String} year The contract year reference
	     * @apiParam {String} id The contract id reference no
	     * @apiGroup Contract
	     * 
	     * @apiSuccess {Object} contract A commodity exchange contract item
	     * @apiSuccess {String} contract.contractDate 
	     * @apiSuccess {String} contract.contractReference 
	     * @apiSuccess {String} contract.commodityName 
	     * @apiSuccess {String} contract.volumeInMetricTonnes
	     * @apiSuccess {String} contract.variety
	     * @apiSuccess {String} contract.grade
	     * @apiSuccess {String} contract.valueUoM
	     * @apiSuccess {String} contract.inco
	     */
		Spark.get("/contracts/:year/:id", APPLICATION_JSON, (request, response) -> {
			response.type(APPLICATION_JSON_UTF8);
			
			String contractReference = request.params("year").concat("/")
					.concat(request.params("id"));
			
			return contracts.findByReference(contractReference).orElse(null);
			
		}, new JsonTransformer());
		LOG.info("Registered /contracts/:year/:id");
	}
	
	private ContractsCollection loadData()  {
		return this.parser.parseFromUri(this.dataUrl);
	}
}
