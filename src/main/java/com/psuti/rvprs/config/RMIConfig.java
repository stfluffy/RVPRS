package com.psuti.rvprs.config;

import com.psuti.rvprs.rmi.ProductServiceRMI;
import com.psuti.rvprs.rmi.impl.ProductServiceRMIImpl;
import com.psuti.rvprs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
@RequiredArgsConstructor
public class RMIConfig {

    private final ProductService productService;

    @Bean
    RemoteExporter registerRMIExporter() {

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("product_service");
        exporter.setServiceInterface(ProductServiceRMI.class);
        exporter.setService(new ProductServiceRMIImpl(productService));
        exporter.setRegistryPort(1099);

        return exporter;
    }

}
