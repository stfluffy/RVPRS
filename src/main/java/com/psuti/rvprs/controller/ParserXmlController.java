package com.psuti.rvprs.controller;

import com.psuti.rvprs.response.ParserXMLResponse;
import com.psuti.rvprs.service.ParserXml;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * @author Modenov D.A.
 */

@Controller
@RequestMapping("/labs/first")
@RequiredArgsConstructor
public class ParserXmlController {

    private final ParserXml parserXml;

    @GetMapping
    public String index(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message != null ? message : "");
        return "parserXml";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)  {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Пожалуйста выберите файл");
            return "redirect:/labs/first";
        }

        ParserXMLResponse parserXMLResponse = parserXml.validateXmlFile(file);

        if (parserXMLResponse.hasError()) {
            redirectAttributes.addFlashAttribute("message", parserXMLResponse.getError());
            return "redirect:/labs/first";
        }

        InputStreamResource resource = new InputStreamResource(parserXMLResponse.getByteArrayInputStream());

        redirectAttributes.addFlashAttribute("file", resource);
        return "redirect:/labs/first/download";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute(name = "file") InputStreamResource resource) {

        if (Objects.isNull(resource)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "Validate.xml")
                .header("Content-Type", "application/xml; charset=utf-8")
                .body(resource);
    }

}
