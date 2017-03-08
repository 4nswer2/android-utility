package jp.gr.java_conf.atle42.androidutility;

import android.util.Log;

import com.annimon.stream.Stream;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by murata_to on 2016/12/08.
 */

public class HttpUtil {
	private static final String TAG = "HttpUtil";

	public enum Method {
		GET,
		POST,
		PUT,
		PATCH,
		DELETE
	}

	public static Result<Response> request(Method method, String url, Map<String, Object> query, Map<String, Object> body, Map<String, String> header, Long timeoutSeconds) {
		Response response = null;
		Error error = null;

		if (query  == null)  { query  = new HashMap<>(); }
		if (body   == null)  { body   = new HashMap<>(); }
		if (header == null)  { header = new HashMap<>(); }

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		if (timeoutSeconds != null) {
			builder.connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
					.readTimeout(timeoutSeconds, TimeUnit.SECONDS)
					.writeTimeout(timeoutSeconds, TimeUnit.SECONDS);
		}
		OkHttpClient okHttpClient = builder.build();

		Request.Builder requestBuilder = new Request.Builder().url(url + toQueryString(query));
		Stream.of(header.entrySet()).forEach(entry -> requestBuilder.addHeader(entry.getKey(), entry.getValue()));
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(body, Map.class));
		switch (method) {
			case GET:    requestBuilder.get();              break;
			case POST:   requestBuilder.post(requestBody);  break;
			case PUT:    requestBuilder.put(requestBody);   break;
			case PATCH:  requestBuilder.patch(requestBody); break;
			case DELETE: requestBuilder.delete();           break;
		}
		Request request = requestBuilder.build();
		Log.d(TAG, "[request]" + request.url());

		try {
			response = okHttpClient.newCall(request).execute();
		} catch (IOException e) {
			error = new Error(e.getMessage());
		}

		return new Result(response, error);
	}

	public static String urlEncode(String text) {
		if (text == null) {
			return "";
		}

		try {
			return URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	//--------------------------------------------------
	//  private method
	//--------------------------------------------------
	private static String toQueryString(Map<String, Object> queries) {
		if (queries == null || queries.isEmpty()) { return ""; }

		StringBuilder builder = new StringBuilder("?");
		Stream.of(queries.entrySet()).forEach(entry -> builder.append(entry.getKey() + "=" + entry.getValue() + "&"));
		builder.deleteCharAt(builder.length() - 1);

		return builder.toString();
	}
}
