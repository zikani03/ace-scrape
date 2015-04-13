package io.github.zikani03.acescrape.data;

import java.util.Optional;
import java.util.Set;

import org.eclipse.jetty.util.ConcurrentHashSet;

public class MarketCollection {	
	
	private final Set<Market> markets;
		
	public MarketCollection() {
		this.markets = new ConcurrentHashSet<Market>();
	}
	
	public void addMarket(Market market) {
		this.markets.add(market);
	}
	
	public Optional<Market> findByName(String marketName) {
		return this.markets
				.stream()
				.filter(market -> market.getMarketName().equalsIgnoreCase(marketName))
				.findFirst();
	}
}
