package com.example.network;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;
import java.net.URI;
import java.util.HashMap;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.network.api.Http;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import static com.xtremelabs.robolectric.util.Strings.fromStream;

@RunWith(RobolectricTestRunner.class)
public class HttpTest {
    private Http http;

    @Before
    public void setup() {
        http = new Http();
        Robolectric.setDefaultHttpResponse(200, "OK");
    }

    @Test
    public void tetGetWithOutHeader() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");
        http.get("www.example.com", new HashMap<String, String>());
        assertThat(((HttpUriRequest) Robolectric.getSentHttpRequest(0)).getURI(),
                equalTo(URI.create("www.example.com")));
    }

    @Test
    public void tetGetWithHeader() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("key", "value");
        http.get("www.example.com", headers);
        HttpRequest sentHttpRequest = Robolectric.getSentHttpRequest(0);
        assertThat(sentHttpRequest.getHeaders("key")[0].getValue(), equalTo("value"));
    }

    @Test
    public void tetGetCorrectMethod() throws Exception {
        http.get("www.example.com", new HashMap<String, String>());
        HttpUriRequest sentHttpRequest = (HttpUriRequest) Robolectric.getSentHttpRequest(0);
        assertThat(sentHttpRequest.getMethod(), equalTo(HttpGet.METHOD_NAME));
    }
    
    @Test
    public void shouldReturnCorrectResponse() throws Exception {
        Robolectric.addPendingHttpResponse(400, "It's ok");

        HttpResponse response = http.get("www.example.com", new HashMap<String, String>());

        assertThat(fromStream(response.getEntity().getContent()), equalTo("It's ok h"));
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }
}
