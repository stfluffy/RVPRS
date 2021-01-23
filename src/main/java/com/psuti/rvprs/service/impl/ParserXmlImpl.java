package com.psuti.rvprs.service.impl;

import com.psuti.rvprs.model.ProductXML;
import com.psuti.rvprs.model.StoreXML;
import com.psuti.rvprs.response.ParserXMLResponse;
import com.psuti.rvprs.response.ParserXMLValidationResponse;
import com.psuti.rvprs.service.ParserXml;
import com.psuti.rvprs.service.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
 * @author Modenov D.A.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserXmlImpl implements ParserXml {

    private final JAXBContext context;

    private final UploadFile uploadFile;

    @Override
    public ParserXMLResponse validateXmlFile(MultipartFile file) {

        ByteArrayInputStream upload = uploadFile.upload(file);

        if (Objects.isNull(upload)) {
            return new ParserXMLResponse("Не удается загрузить файл!");
        }

        StoreXML storeXML = unMarshall(upload);

        if (Objects.isNull(storeXML)) {
            return new ParserXMLResponse("Не удается прочитать файл, проверьте формат файла!");
        }

        StoreXML validate = validate(storeXML);

        if (Objects.isNull(validate)) {
            return new ParserXMLResponse("Валидация не пройдена!");
        }

        ByteArrayInputStream marshall = marshall(validate);

        if (Objects.isNull(marshall)) {
            return new ParserXMLResponse("Не удается создать файл!");
        }

        return new ParserXMLResponse(marshall);
    }

    private StoreXML validate(StoreXML storeXML) {
        if (Objects.isNull(storeXML)) {
            return null;
        }

        if (Objects.isNull(storeXML.getShelves()) || storeXML.getShelves().isEmpty()) {
            storeXML.setTotalProducts(0L);
            return storeXML;
        }

        long count = storeXML.getShelves().stream()
                .mapToLong(shelf -> shelf.getProducts().stream()
                        .filter(Objects::nonNull)
                        .mapToLong(p -> {
                            if (p.getQuantity() != null) {
                                return p.getQuantity();
                            }
                            return 0;
                        })
                        .sum()).sum();

        if (Objects.isNull(storeXML.getTotalProducts()) || count != storeXML.getTotalProducts()) {
            storeXML.setTotalProducts(count);
        }

        return storeXML;
    }

    private ByteArrayInputStream marshall(StoreXML storeXML) {
        try {
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE store SYSTEM \"store.dtd\">");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mar.marshal(storeXML, byteArrayOutputStream);

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    private StoreXML unMarshall(ByteArrayInputStream byteArrayInputStream) {
        try {
            XMLInputFactory xif = XMLInputFactory.newFactory();
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(byteArrayInputStream);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (StoreXML) unmarshaller.unmarshal(xsr);
        } catch (Exception e) {
            return null;
        }
    }

}
