package com.ifi;

import com.ifi.stateless.HelloWorld;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Program Exited");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        Scanner scanner = new Scanner(System.in);
        try {
            invokeRemoteBean();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.print("Press 0 to exit: ");
            int input = scanner.nextInt();
            if (input == 0) {
                System.exit(0);
            }
        }
    }

    private static void invokeRemoteBean() throws NamingException {
        final HelloWorld helloWorldBean = lookupBean();
        if (helloWorldBean == null) {
            return;
        }
        System.out.println(helloWorldBean.sayHello());
    }

    private static HelloWorld lookupBean() throws NamingException {
        final Properties jndiProperties = buildJNDIProperties();
        final Context context = new InitialContext(jndiProperties);
        return (HelloWorld) context.lookup("ejb:/ejb-remote-1.0/HelloWorld!com.ifi.stateless.HelloWorld");
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
