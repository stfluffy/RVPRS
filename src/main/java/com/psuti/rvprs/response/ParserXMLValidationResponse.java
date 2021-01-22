package com.psuti.rvprs.response;

import com.psuti.rvprs.model.StoreXML;

import java.util.Objects;

public class ParserXMLValidationResponse {

    private final StoreXML storeXML;

    private final String error;

    public ParserXMLValidationResponse(StoreXML storeXML) {
        this.storeXML = storeXML;
        this.error = null;
    }

    public ParserXMLValidationResponse(String error) {
        this.storeXML = null;
        this.error = error;
    }

    public StoreXML getByteArrayInputStream() {
        return storeXML;
    }

    public String getError() {
        return error;
    }

    public boolean hasError() {
        return Objects.nonNull(error);
    }
}
