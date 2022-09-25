package org.cuy.nugas.utilities;

public class TemplateResponse {

    public String status;
    public String message;
    public Object data;

    public TemplateResponse() {
    }

    public TemplateResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public TemplateResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
