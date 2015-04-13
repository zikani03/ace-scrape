package io.github.zikani03.acescrape.parser;

import io.github.zikani03.acescrape.data.Market;
import io.github.zikani03.acescrape.data.MarketCollection;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MarketInfoParser extends AbstractInfoParser<MarketCollection> {
	private MarketCollection markets = new MarketCollection();
	
	private final String tableId = "ContentPlaceHolderDefault_BaseMasterContent_ctl00_UIAFMarket_4_gvmarketlist";
	// the first td node at 0 is the one with the Select link, we don't need it?
	private static final int MARKET_NAME = 1;
    private static final int MARKET_CODE = 2;
    private static final int DISTRICT_NAME = 3;
    private static final int REGION = 4;
    private static final int TRADE_AGENT = 5;
    private static final int CELL_NUMBER = 6;
	
	/*
	 * HTML Page as at 2015-04-11 will have HTML structure like
	 * 
	 * <code>
		 <div class="text_padding_main">
		    <div>
		        <table cellspacing="0" rules="all" border="1" id="ContentPlaceHolderDefault_BaseMasterContent_ctl00_UIAFMarket_4_gvmarketlist" style="border-collapse:collapse;">
		            <tr class="GridHeader">
		              <th scope="col">&#160;</th>
		              <th scope="col">
		                <a href="javascript:(...)">MarketName</a>
		              </th>
		              <th scope="col">
		                <a href="javascript:(...)">Market Code</a>
		              </th>
		              <th scope="col">
		                <a href="javascript:(...)">District</a>
		              </th>
		              <th scope="col">
		                <a href="javascript:(...)">Region</a>
		              </th>
		              <th scope="col">
		                <a href="javascript:(...)">Trade Agent</a>
		              </th>
		              <th scope="col">
		                <a href="javascript:(...)">Cell Number</a>
		              </th>
		            </tr>
		
		            <tr class="DetailsviewRow">
		              <td>
		                <a href="javascript:(...)">Select</a>
		              </td>
		              <td>$MARKET_NAME</td>
		              <td>MARKET_CODE</td>
		              <td>DISTRICT_NAME</td>
		              <td>REGION</td>
		              <td>TRADE_AGENT</td>
		              <td>CELL_NUMBER</td>
		            </tr>
		            .
		            .
		            .
		        </table>
		    </div>
		</div>
     * </code>
     * 
		<tr class="DetailsviewRow">
		  <td>
		    <a href="javascript:__doPostBack('ctl00$ctl00$ctl00$ContentPlaceHolderDefault$BaseMasterContent$ctl00$UIAFMarket_4$gvmarketlist','Select$24')">
		    Select</a>
		  </td>
		  <td>Limbe</td>
		  <td>LIMB</td>
		  <td>BLANTYRE</td>
		  <td>Undifined</td>
		  <td>Phillip Mwale</td>
		  <td align="center" style="width:100px;">265999180459</td>
		</tr>
		<tr class="Alternating">
		  <td>
		    <a href="javascript:__doPostBack('ctl00$ctl00$ctl00$ContentPlaceHolderDefault$BaseMasterContent$ctl00$UIAFMarket_4$gvmarketlist','Select$25')">
		    Select</a>
		  </td>
		  <td>KASUNGU BOMA</td>
		  <td>KSGB</td>
		  <td>KASUNGU</td>
		  <td>Undifined</td>
		  <td>Mercy Mwanjara</td>
		  <td align="center" style="width:100px;">265884943592</td>
		</tr>
	 *
	 */	
    @Override
	protected MarketCollection parseToCollection() {
		StringBuilder sb = new StringBuilder();
		
		// Build selector
		String baseSelector = sb.append("#").append(this.tableId).append(" ").toString();
		// #id > tr.
		// Get the elements with class .DetailsViewRow
		
		Elements elements = document.select(baseSelector.concat(" tr.DetailsviewRow"));

		for(Element tr: elements) {
			this.markets.addMarket(createMarketFromElement(tr));
		}
		
		// Get the elements with class .Alternating
		elements = document.select(baseSelector.concat(" tr.Alternating"));

		for(Element tr: elements) {
			this.markets.addMarket(createMarketFromElement(tr));
		}
		
		return markets;
	}
	
	protected Market createMarketFromElement(Element tr) {		
		return new Market(tr.child(MARKET_NAME).text(),
				tr.child(MARKET_CODE).text(),
				tr.child(DISTRICT_NAME).text(),
				tr.child(REGION).text(),
				tr.child(TRADE_AGENT).text(),
				tr.child(CELL_NUMBER).text());
	}
}
