package com.psuti.rvprs.config;

import com.psuti.rvprs.model.StoreXML;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class JAXBParser {

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(StoreXML.class);
    }
}
