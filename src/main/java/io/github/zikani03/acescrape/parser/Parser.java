package io.github.zikani03.acescrape.parser;

public interface Parser<T> {
	T parseFromUri(String baseUri);
	T parseFromHtml(String html);
}
