package com.psuti.rvprs.service;

import com.psuti.rvprs.response.ParserXMLResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Modenov D.A.
 */

public interface ParserXml {

    /**
     *
     * @param file
     * @return
     */
    ParserXMLResponse validateXmlFile(MultipartFile file);

}
