package br.com.projetoAlexa.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Http3Service {
	
	public static <T> Object BuscaServicoDeOutrasApi(String https_url, Class<T> classe) throws IOException {
		int cacheSize = 10 * 1024 * 1024;
		Cache cache = new Cache(new File(".", "http_cache"), cacheSize);

		OkHttpClient client = new OkHttpClient.Builder()
			    .cache(cache)
			    .build();
		CacheControl cacheControl = new CacheControl.Builder()
                .maxAge( 2, TimeUnit.MINUTES )
                .build();
		
		Request request = new Request.Builder().cacheControl(cacheControl).url(https_url).build();
		Response response = client.newCall(request).execute();

		return new Gson().fromJson(response.body().string(), classe);
	}
	
	
}