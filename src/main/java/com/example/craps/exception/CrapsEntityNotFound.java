package com.example.craps.exception;

public class CrapsEntityNotFound extends BaseCrapsException{
    private static String exp = "Entity not found!";

    @Override
    public int getStatus() {
        return 404;
    }

    public CrapsEntityNotFound(){
        super();
    }

    public CrapsEntityNotFound(String message) {
        super(exp,message);
    }

    public CrapsEntityNotFound(String exp,String message) {
        super(exp,message);
    }

    public CrapsEntityNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CrapsEntityNotFound(Throwable cause) {
        super(exp,cause);
    }

    public CrapsEntityNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(exp, message, cause, enableSuppression, writableStackTrace);
    }
}
