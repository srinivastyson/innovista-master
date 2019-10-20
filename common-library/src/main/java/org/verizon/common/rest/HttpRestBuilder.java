package org.verizon.common.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.verizon.common.enums.HttpMethod;
import org.verizon.common.rest.request.EntityConstructor;
import org.verizon.common.rest.response.Extractor;
import org.verizon.common.rest.response.HeaderExtractor;
import org.verizon.common.util.ValidationUtils;

public class HttpRestBuilder {
    protected HttpRestBuilder() {
    }

    public Object construct(
        String url,
        HttpMethod method,
        Map<String, String> headers,
        Map<String, String> params,
        EntityConstructor constructor,
        Extractor extractor) {
        CloseableHttpClient httpClient = null;
        try {
            // Create client
            httpClient = HttpClientBuilder.create().build();

            HttpRequestBase request = this.initializeRequest(method, url, constructor);

            // Add Headers
            this.addHeaders(request, headers);

            // Execute your request and catch response
            HttpResponse response = httpClient.execute(request);

            // Check for HTTP response code: 200 = success
            /*if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }*/

            // Extract from response
            return extractor.extractResponse(response);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public HttpRequestBase initializeRequest(HttpMethod method, String url, EntityConstructor constructor) {
        if (HttpMethod.GET.equals(method)) {
            return this.get(url);
        } else if (HttpMethod.POST.equals(method)) {
            return this.post(url, constructor);
        } else if (HttpMethod.PUT.equals(method)) {
            return this.put(url);
        } else {
            return this.get(url);
        }
    }

    public HttpGet get(String url) {
        return new HttpGet(url);
    }

    public HttpPost post(String url, EntityConstructor constructor) {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(constructor.constructEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpPost;
    }

    public HttpPut put(String url) {
        return new HttpPut(url);
    }

    public void addHeaders(HttpRequestBase request, Map<String, String> headers) {
        Iterator<String> headerKeyset = headers.keySet().iterator();
        while(headerKeyset.hasNext()) {
            String key = headerKeyset.next();
            request.addHeader(key, headers.get(key));
        }
    }

    /***** Builder Pattern *****/
	public static class Builder {
	    private String url;
	    private HttpMethod method;
	    private Map<String, String> headers;
	    private Map<String, String> params;
	    private Object requestObj;

	    private EntityConstructor constructor;
        private Extractor extractor;

        public Builder(String url) {
            if (ValidationUtils.validateURL(url)) {
                this.url = url;
                this.headers = new HashMap<String, String>();
                this.params = new HashMap<String, String>();
                this.extractor = new HeaderExtractor();
            }
        }

        public Builder get() {
            this.method = HttpMethod.GET;
            return this;
        }

        public Builder post() {
            this.method = HttpMethod.POST;
            return this;
        }

        public Builder put() {
            this.method = HttpMethod.PUT;
            return this;
        }

        public Builder header(String header, String value) {
            if (ValidationUtils.isNotEmpty(header) && ValidationUtils.isNotEmpty(value)) {
                this.headers.put(header, value);
            }
            return this;
        }

        public Builder param(String param, String value) {
            if (ValidationUtils.isNotEmpty(param) && ValidationUtils.isNotEmpty(value)) {
                this.params.put(param, value);
            }
            return this;
        }

        public Builder requestConstructor(EntityConstructor constructor) {
            this.constructor = constructor;
            return this;
        }

        public Builder responseExtractor(Extractor extractor) {
            this.extractor = extractor;
            return this;
        }

        @Override
        public String toString() {
            return "Builder [" + url + " " + method + " "
                    + (null != requestObj ? requestObj.getClass().getSimpleName() : "") + " "
                    + (null != constructor ? constructor.getClass().getSimpleName() : "") + " "
                    + (null != extractor ? extractor.getClass().getSimpleName() : "") + "]";
        }

        public Object build() {
            if (null != requestObj && null == constructor) {
                throw new IllegalArgumentException("For submitting a Request Object a Constructor is required.");
            }
            if (null == extractor) {
                throw new IllegalArgumentException("For receiving a Response an Extractor is required.");
            }
            return new HttpRestBuilder().construct(
                this.url, this.method, this.headers, this.params, this.constructor, this.extractor);
        }
	}
}
