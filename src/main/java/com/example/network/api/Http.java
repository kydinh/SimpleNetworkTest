package com.example.network.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import android.util.Log;

public class Http {
    
    private DefaultHttpClient httpClient;
    
    public HttpResponse get(String url, Map<String, String>headers) throws URISyntaxException{
        URI uri = new URI(url);
        Log.d("Debug2", url);
        return makeRequest(headers, new HttpGet(uri), uri.getHost());
    }
    
    public HttpResponse post(String url, Map<String, String>headers, String body) throws URISyntaxException, UnsupportedEncodingException{
        URI uri = new URI(url);
        HttpPost method = new HttpPost(uri);
        method.setEntity(new StringEntity(body, "UTF-8"));
        return makeRequest(headers,  method, uri.getHost());
    }
    
    private HttpResponse makeRequest(Map<String, String> headers,  HttpRequestBase method, String host) {
        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                method.setHeader(entry.getKey(), entry.getValue());
            }
            DefaultHttpClient client = getHttpClient();
            Log.d("Debug3", "ddd");
            return client.execute(method);
        } catch (IOException e) {
            
            throw new RuntimeException(e);
        }
    }
    
    private DefaultHttpClient getHttpClient() {
        if (httpClient == null) {
            HttpParams parameters = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(parameters, schemeRegistry), parameters);
        }
        return httpClient;
    }
}
