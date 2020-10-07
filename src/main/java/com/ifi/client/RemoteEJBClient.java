package com.ifi.client;

import com.ifi.stateless.HelloWorld;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class RemoteEJBClient {
    public static void main(String[] args) throws NamingException {
        invokeStatelessBean();
    }

    private static void invokeStatelessBean() throws NamingException {
        final HelloWorld helloWorldBean = lookupBean();
        if (helloWorldBean != null) {
            System.out.println(helloWorldBean.sayHello());
        } else {
            System.out.println("Bean not found!");
        }
    }

    private static HelloWorld lookupBean() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        return (HelloWorld) context.lookup("ejb:/ejb-sample-1.0/HelloWorld!com.ifi.stateless.HelloWorld");
    }
}
