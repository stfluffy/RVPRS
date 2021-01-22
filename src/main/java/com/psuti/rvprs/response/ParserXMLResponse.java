package com.psuti.rvprs.response;

import java.io.ByteArrayInputStream;
import java.util.Objects;


public class ParserXMLResponse {

    private final ByteArrayInputStream byteArrayInputStream;

    private final String error;

    public ParserXMLResponse(ByteArrayInputStream byteArrayInputStream) {
        this.byteArrayInputStream = byteArrayInputStream;
        this.error = null;
    }

    public ParserXMLResponse(String error) {
        this.byteArrayInputStream = null;
        this.error = error;
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        return byteArrayInputStream;
    }

    public String getError() {
        return error;
    }

    public boolean hasError() {
        return Objects.nonNull(error);
    }
}
