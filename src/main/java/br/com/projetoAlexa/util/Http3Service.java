package br.com.projetoAlexa.util;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Http3Service {
	
	public static <T> Object BuscaServicoDeOutrasApi(String https_url, Class<T> classe) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(https_url).build();
		Response response = client.newCall(request).execute();

		return new Gson().fromJson(response.body().string(), classe);
	}
}