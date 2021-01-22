package com.psuti.rvprs.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;

/**
 * @author Modenov D.A.
 */

public interface UploadFile {

    /**
     *
     * @param file
     * @return
     */
    ByteArrayInputStream upload(MultipartFile file);
}
