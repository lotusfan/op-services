package com.opservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yellowcar.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: renpengshang
 * Date: 14-8-13
 * Time: 下午9:06
 */
@SuppressWarnings("StandardVariableNames")
public final class Request<T extends Body> {
    static final transient Logger LOGGER = LoggerFactory.getLogger(Request.class);
    private static final int MAX_REQUEST_PARAMETERS = 30;
    private Pre p = null; //pre
    private T b = null; //Body

    public Pre getP() {
        return p;
    }

    public void setP(final Pre p) {
        this.p = p;
    }

    public T getB() {
        return b;
    }

    public void setB(final T b) {
        this.b = b;
    }

    @SuppressWarnings("PublicInnerClass")

    public static final class Pre {
        private ClientType t = null;   //clientType
        private String d = null;       //deviceId
        private long v = 0L;         //clientVersion
        private String n = null;         //nonce

        public ClientType getT() {
            return t;
        }

        public void setT(final ClientType t) {
            this.t = t;
        }

        public String getD() {
            return d;
        }

        public void setD(final String d) {
            this.d = d;
        }

        public long getV() {
            return v;
        }

        public void setV(final long v) {
            this.v = v;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        @JsonIgnore
        public Map<String,Object> toMap(){
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("{} become to map." , this);
            }
            if (StringUtils.isEmpty(d) || StringUtils.isEmpty(n) ||(t == null)){
//                throw new PmsBadRequestException("Missing pre parameters.");
            }
            final Map<String,Object> map = new HashMap<String,Object>(4);
            map.put("clientType",t);
            map.put("deviceId",d);
            map.put("clientVersion",v);
            map.put("nonce",n);

            return map;
        }

        @SuppressWarnings("PublicMethodWithoutLogging")
        @Override
        public String toString() {
            return "Pre{" +
                    "t=" + t +
                    ", d='" + d + '\'' +
                    ", v=" + v +
                    ", n=" + n +
                    '}';
        }
    }

    @SuppressWarnings("PublicMethodWithoutLogging")
    @Override
    public String toString() {
        return "Request{" +
                "p=" + p +
                ", b=" + b +
                '}';
    }
}
