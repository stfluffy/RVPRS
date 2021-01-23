package com.rvprs.client.config;

import com.rvprs.client.rmiinterface.ProductServiceRMI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RMIConfig {

    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ProductServiceRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/product_service");

        return bean;
    }

}
