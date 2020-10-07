package com.ifi.stateless;

import javax.ejb.Stateless;

@Stateless(name = "HelloWorld")
public class HelloWorldBean implements HelloWorld {
    @Override
    public String sayHello() {
        return "Hello World";
    }

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
