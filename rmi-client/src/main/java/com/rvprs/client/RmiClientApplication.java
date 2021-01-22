package com.rvprs.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.rmi.RMISecurityManager;

@SpringBootApplication
public class RmiClientApplication {

    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ProductServiceRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/product_service");

        return bean;
    }

    public static void main(String[] args) {
        ProductServiceRMI helloWorldRMI = SpringApplication.run(RmiClientApplication.class, args)
                .getBean(ProductServiceRMI.class);


        System.out.println("================Client Side ========================");

        System.out.println(helloWorldRMI.getAll());

        System.out.println(helloWorldRMI.getById(10L));
    }

}
