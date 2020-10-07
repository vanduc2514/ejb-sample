package com.ifi.stateless;

import javax.ejb.Remote;

@Remote
public interface HelloWorld {
    String sayHello();

    String sayHello(String name);
}
