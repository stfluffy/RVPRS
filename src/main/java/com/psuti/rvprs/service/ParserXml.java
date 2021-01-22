package com.psuti.rvprs.service;

import com.psuti.rvprs.response.ParserXMLResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ParserXml {

    ParserXMLResponse validateXmlFile(MultipartFile file);

}
