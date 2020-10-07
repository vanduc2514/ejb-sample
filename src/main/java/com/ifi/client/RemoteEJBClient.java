package com.ifi.client;

import com.ifi.stateless.HelloWorld;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

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
        final Properties jndiProperties = buildJNDIProperties();
        final Context context = new InitialContext(jndiProperties);
        return (HelloWorld) context.lookup("ejb:/ejb-sample-1.0/HelloWorld!com.ifi.stateless.HelloWorld");
    }

    private static Properties buildJNDIProperties() {
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        return jndiProperties;
    }
}
