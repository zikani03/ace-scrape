package io.github.zikani03.acescrape.client;

import java.io.IOException;
import java.util.function.Function;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public abstract class AbstractHttpClient<T> {

	public T execute(String uriPath, Function<HttpResponse, T> handler) throws ClientProtocolException, IOException {
		
		HttpClient client =  HttpClientBuilder.create().build();
		
		return client.execute(this.createRequest(uriPath), new ResponseHandler<T>() {
			@Override
			public T handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				return handler.apply(response);
			}
		});		
	}
	
	public HttpUriRequest createRequest(String path) {
		HttpUriRequest request = null;
		
		return request;
	}
}
