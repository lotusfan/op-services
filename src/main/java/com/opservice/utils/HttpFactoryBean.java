package com.opservice.utils;

//import com.ning.http.client.AsyncHttpClient;
//import com.ning.http.client.AsyncHttpClientConfig;
//import com.ning.http.client.providers.netty.NettyAsyncHttpProvider;
//import org.springframework.beans.factory.FactoryBean;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.providers.netty.NettyAsyncHttpProvider;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by shangrenpeng on 11/27/14.
 */
public class HttpFactoryBean implements FactoryBean<AsyncHttpClient> {

    final private AsyncHttpClient asyncHttpClient;
    private int maxConnectionLifeTimeInMs = 500;
    private int maxTotalConnections = 1000;
    private int connectionTimeOutInMs = 6000;
    private int requestTimeoutInMs = 6000;
    private int maxRequestRetry = 3;

    public HttpFactoryBean(){
        AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
        builder.setMaxConnectionLifeTimeInMs(maxConnectionLifeTimeInMs);
        builder.setMaximumConnectionsTotal(maxTotalConnections);
        builder.setConnectionTimeoutInMs(connectionTimeOutInMs);
        builder.setRequestTimeoutInMs(requestTimeoutInMs);
        builder.setMaxRequestRetry(maxRequestRetry);
        final AsyncHttpClientConfig config = builder.build();
        asyncHttpClient = new AsyncHttpClient(new NettyAsyncHttpProvider(config), config);
    }

    public int getMaxConnectionLifeTimeInMs() {
        return maxConnectionLifeTimeInMs;
    }

    public void setMaxConnectionLifeTimeInMs(int maxConnectionLifeTimeInMs) {
        this.maxConnectionLifeTimeInMs = maxConnectionLifeTimeInMs;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getConnectionTimeOutInMs() {
        return connectionTimeOutInMs;
    }

    public void setConnectionTimeOutInMs(int connectionTimeOutInMs) {
        this.connectionTimeOutInMs = connectionTimeOutInMs;
    }

    public int getRequestTimeoutInMs() {
        return requestTimeoutInMs;
    }

    public void setRequestTimeoutInMs(int requestTimeoutInMs) {
        this.requestTimeoutInMs = requestTimeoutInMs;
    }

    public int getMaxRequestRetry() {
        return maxRequestRetry;
    }

    public void setMaxRequestRetry(int maxRequestRetry) {
        this.maxRequestRetry = maxRequestRetry;
    }

    @Override
    public AsyncHttpClient getObject() throws Exception {
        return asyncHttpClient;
    }

    @Override
    public Class<?> getObjectType() {
        return AsyncHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
