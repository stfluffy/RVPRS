package com.psuti.rvprs.service.impl;

import com.psuti.rvprs.service.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * @author Modenov D.A.
 */

@Service
@Slf4j
public class UploadFileImpl implements UploadFile {

    @Override
    public ByteArrayInputStream upload(MultipartFile file) {
        try {
            return new ByteArrayInputStream(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
