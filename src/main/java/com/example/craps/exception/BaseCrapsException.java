package com.example.craps.exception;

public class BaseCrapsException extends Exception{
    protected String code="Default message!";
    private int status = 500;

    public BaseCrapsException(String exp) {
    }

    public BaseCrapsException(String exp, String code, Throwable cause) {
    }

    public BaseCrapsException(String exp, String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }

    public int getStatus() {
        return status;
    }

    public BaseCrapsException(){}


    public BaseCrapsException(String code,String message ) {
        super(message);
        this.code = code;
    }


    public BaseCrapsException( String code,Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseCrapsException( String code,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
