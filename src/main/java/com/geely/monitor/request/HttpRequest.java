package com.geely.monitor.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bin.Zeng1
 * @date 2019/11/16
 **/
public class HttpRequest {

    private static final Integer SUCCESS_CODE = 200;
    private static final String POST = "post";
    private static final String GET = "get";
    private static final String DELETE = "delete";
    private static final String PUT = "put";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);

    /**
     * 发送GET请求
     *
     * @param url               请求url
     * @param nameValuePairList 请求参数
     * @return JSON或者字符串
     * @throws Exception
     */
    public static JSONObject sendGet(String url, List<NameValuePair> nameValuePairList, String token) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(nameValuePairList);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(new BasicHeader("token", token));
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                try {
                    return JSONObject.parseObject(result);
                } catch (Exception e) {
                    LOGGER.error(String.format("FileVerify-sendGet parseObject exception, token=%s", token), e);
                }
            } else {
                LOGGER.error(String.format("FileVerify-sendGet request fail, statusCode=%s, token=%s", statusCode, token));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("FileVerify-sendGet request exception, token=%s", token), e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url               请求url
     * @param nameValuePairList 请求参数
     * @return JSON或者字符串
     * @throws Exception
     */
    public static JSONObject sendPost(String url, List<NameValuePair> nameValuePairList, String token) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            post.setEntity(entity);
            post.setHeader(new BasicHeader("token", token));
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                try {
                    return JSONObject.parseObject(result);
                } catch (Exception e) {
                    LOGGER.error(String.format("FileVerify-sendPost parseObject exception, token=%s", token), e);
                }
            } else {
                LOGGER.error(String.format("FileVerify-sendPost request fail, statusCode=%s, token=%s", statusCode, token));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("FileVerify-sendPost request exception, token=%s", token), e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 发送DELETE请求
     *
     * @param url               请求url
     * @param nameValuePairList 请求参数
     * @return JSON或者字符串
     * @throws Exception
     */
    public static JSONObject sendDelete(String url, List<NameValuePair> nameValuePairList, String token) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            HttpDelete delete = new HttpDelete(url);
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            delete.setHeader(new BasicHeader("token", token));
            response = client.execute(delete);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                try {
                    return JSONObject.parseObject(result);
                } catch (Exception e) {
                    LOGGER.error(String.format("FileVerify-sendDelete parseObject exception, token=%s", token), e);
                }
            } else {
                LOGGER.error(String.format("FileVerify-sendDelete request fail, statusCode=%s, token=%s", statusCode, token));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("FileVerify-sendDelete request exception, token=%s", token), e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 发送PUT请求
     *
     * @param url               请求url
     * @param nameValuePairList 请求参数
     * @return JSON或者字符串
     * @throws Exception
     */
    public static JSONObject sendPut(String url, List<NameValuePair> nameValuePairList, String token) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            HttpPut put = new HttpPut(url);
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            put.setHeader(new BasicHeader("token", token));
            response = client.execute(put);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                try {
                    return JSONObject.parseObject(result);
                } catch (Exception e) {
                    LOGGER.error(String.format("FileVerify-sendPut parseObject exception, token=%s", token), e);
                }
            } else {
                LOGGER.error(String.format("FileVerify-sendPut request fail, statusCode=%s, token=%s", statusCode, token));
            }
        } catch (Exception e) {
            LOGGER.error(String.format("FileVerify-sendPut request exception, token=%s", token), e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 组织请求参数{参数名和参数值下标保持一致}
     *
     * @param params 参数名数组
     * @param values 参数值数组
     * @return 参数对象
     */
    public static List<NameValuePair> getParams(Object[] params, Object[] values) {
        boolean flag = params.length > 0 && values.length > 0 && params.length == values.length;
        if (flag) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                nameValuePairList.add(new BasicNameValuePair(params[i].toString(), values[i].toString()));
            }
            return nameValuePairList;
        } else {
            LOGGER.error(String.format("FileVerify-getParams validate fail, params=%s, values=%s", JSON.toJSON(params), JSON.toJSON(values)));
        }
        return null;
    }

    /**
     * 发起请求
     *
     * @param requestUrl
     * @param requestMethod
     * @param params
     * @param values
     * @param token
     * @return
     * @throws Exception
     */
    public JSONObject request(String requestUrl, String requestMethod, Object[] params, Object[] values, String token) throws Exception {
        List<NameValuePair> nameValuePairList = HttpRequest.getParams(params, values);
        if (POST.equals(requestMethod)) {
            return HttpRequest.sendPost(requestUrl, nameValuePairList, token);
        } else if (GET.equals(requestMethod)) {
            return HttpRequest.sendGet(requestUrl, nameValuePairList, token);
        } else if (PUT.equals(requestMethod)) {
            return HttpRequest.sendPut(requestUrl, nameValuePairList, token);
        } else {
            return HttpRequest.sendDelete(requestUrl, nameValuePairList, token);
        }
    }
}
