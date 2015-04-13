package io.github.zikani03.acescrape;

public interface Controller {
	static String APPLICATION_JSON = "application/json";
	static String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
	void registerRoutes();
}