package com.opservice;

/**
 * Created with IntelliJ IDEA.
 * User: renpengshang
 * Date: 14-8-13
 * Time: 下午9:06
 */
@SuppressWarnings("StandardVariableNames")
public class Response<T extends Body> {
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

    public static class Pre {
        private int c = 200;   //clientType
        private String m = null;       //message

        public int getC() {
            return c;
        }

        public void setC(final int c) {
            this.c = c;
        }

        public String getM() {
            return m;
        }

        public void setM(final String m) {
            this.m = m;
        }


        @Override
        public String toString() {
            return "Pre{" +
                    "c=" + c +
                    ", m='" + m + '\'' +
                    '}';
        }
    }
}
