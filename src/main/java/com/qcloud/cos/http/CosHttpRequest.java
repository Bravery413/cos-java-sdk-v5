package com.qcloud.cos.http;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.qcloud.cos.internal.CosServiceRequest;

public class CosHttpRequest<T extends CosServiceRequest> {

    /** The resource path being requested */
    private String resourcePath;


    private Map<String, String> parameters = new HashMap<String, String>();

    /** Map of the headers included in this request */
    private Map<String, String> headers = new HashMap<String, String>();

    /** The service endpoint to which this request should be sent */
    private URI endpoint;

    /** The HTTP method to use when sending this request. */
    private HttpMethodName httpMethod = HttpMethodName.POST;

    /** An optional stream from which to read the request payload. */
    private InputStream content;

    T originRequest;

    public CosHttpRequest(T originRequest) {
        this.originRequest = originRequest;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public CosHttpRequest<T> withParameter(String name, String value) {
        addParameter(name, value);
        return this;
    }

    public HttpMethodName getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethodName httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public T getOriginalRequest() {
        return originRequest;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("endpoint: ").append(this.endpoint).append(", resourcepath: ")
                .append(this.resourcePath).append(", httpMethod: ").append(this.httpMethod);
        
        strBuilder.append(", headers { ");
        for (String headerKey : this.headers.keySet()) {
            String headerValue = this.headers.get(headerKey);
            strBuilder.append(headerKey).append(" : ").append(headerValue).append(", ");
        }
        strBuilder.append("}, params: { ");
        for (String paramKey : this.parameters.keySet()) {
            String paramValue = this.parameters.get(paramKey);
            strBuilder.append(paramKey).append(" : ").append(paramValue).append(", ");
        }
        strBuilder.append("}");
        return strBuilder.toString();
    }
}