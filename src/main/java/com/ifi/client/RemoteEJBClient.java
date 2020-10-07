package com.ifi.client;

import com.ifi.stateless.HelloWorld;

public class RemoteEJBClient {
    public static void main(String[] args) {
        invokeStatelessBean();
    }

    private static void invokeStatelessBean() {
        final HelloWorld helloWorldBean = lookupHelloWorldBean();
    }

    private static HelloWorld lookupHelloWorldBean() {
        return null;
    }
}
