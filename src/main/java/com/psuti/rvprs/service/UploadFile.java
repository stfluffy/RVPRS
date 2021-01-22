package com.psuti.rvprs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface UploadFile {

    ByteArrayInputStream upload(MultipartFile file);
}
