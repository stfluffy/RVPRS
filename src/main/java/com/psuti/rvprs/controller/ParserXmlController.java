package com.psuti.rvprs.controller;

import com.psuti.rvprs.response.ParserXMLResponse;
import com.psuti.rvprs.service.ParserXml;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/labs/first")
@RequiredArgsConstructor
public class ParserXmlController {

    private final ParserXml parserXml;

    @GetMapping
    public String index(@RequestParam(defaultValue = "", required = false) String message,  Model model) {
        model.addAttribute("message", message);
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse httpServletResponse)  {

        if (file.isEmpty()) {
//            model.addAttribute("message", "Пожалуйста выберите файл");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/labs/first?message=" + "Пожалуйста выберите файл!");
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }

        ParserXMLResponse parserXMLResponse = parserXml.validateXmlFile(file);

        if (parserXMLResponse.hasError()) {
            model.addAttribute("message", parserXMLResponse.getError());
            return ResponseEntity.ok("upload");
        }

        InputStreamResource resource = new InputStreamResource(parserXMLResponse.getByteArrayInputStream());

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE))
                .body(resource);
    }

}
