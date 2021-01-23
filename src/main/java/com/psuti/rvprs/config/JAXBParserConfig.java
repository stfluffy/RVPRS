package com.psuti.rvprs.config;

import com.psuti.rvprs.model.StoreXML;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @author Modenov D.A.
 */

@Configuration
public class JAXBParserConfig {

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(StoreXML.class);
    }

}
