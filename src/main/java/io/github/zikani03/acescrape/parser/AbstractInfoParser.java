package io.github.zikani03.acescrape.parser;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractInfoParser<T> implements Parser<T> {
	protected Document document;
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractInfoParser.class);
	
	@Override
	public T parseFromUri(String baseUri) {
		
		URL url;
		try {
			LOG.info("Trying to parse document from:  " +  baseUri);
			url = new URL(baseUri);
			document = Jsoup.parse(url, 3000);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			LOG.error("Failed", e);
			return null;
		}
		
		if (document != null) {
			LOG.info("Parsed document successfully from:  " +  baseUri);
			LOG.debug(document.html());
		}
		return this.parseToCollection();
	}

	public T parseFromHtml(String html) {
		document = Jsoup.parse(html);
		return this.parseToCollection();
	}
	
	protected abstract T parseToCollection();
}
