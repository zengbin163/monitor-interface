package com.geely.monitor.resolve.data;

import java.io.Serializable;

/**
 * @author Bin.Zeng1
 * @date 2019/11/17
 **/
public class RequestData implements Serializable {

    private static final long serialVersionUID = -3928832861296252415L;

    private String requestDomain;
    private String requestUrl;
    private String requestMethod;
    private String token;
    private String requestParam;

    public String getRequestDomain() {
        return requestDomain;
    }

    public void setRequestDomain(String requestDomain) {
        this.requestDomain = requestDomain;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
}
