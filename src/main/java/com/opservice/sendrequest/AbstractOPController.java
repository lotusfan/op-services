package com.opservice.sendrequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import com.opservice.Body;
import com.opservice.Request;
import com.opservice.model.PMSMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by colin on 14/12/25.
 */
@Component
public abstract class AbstractOPController {
    @Autowired
    private AsyncHttpClient asyncHttpClient;

    protected PMSMessageModel executeRequest(String url, Request<? extends Body> reqBody)
            throws IOException, ExecutionException, InterruptedException {
        System.out.println(asyncHttpClient);
        com.ning.http.client.Request req = asyncHttpClient.preparePost(url).setHeader("Content-Type", "application/json")
                .setBody(JSON.toJSONString(reqBody).getBytes()).build();

        ListenableFuture<Response> future = asyncHttpClient.executeRequest(req);
        Response resp = future.get();
        if (resp.getStatusCode() != 200) {
            throw new RuntimeException("OP HEAD StatusCode " + resp.getStatusCode());
        }
        com.opservice.Response<PMSMessageModel> response = JSON.parseObject(resp.getResponseBody(), new TypeReference<com.opservice.Response<PMSMessageModel>>() {
        });
        if (response.getP().getC() != 200) {
            throw new RuntimeException("OP Body +" + resp.getResponseBody());
        }
        return response.getB();

    }
}
