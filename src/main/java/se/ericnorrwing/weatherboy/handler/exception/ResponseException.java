package se.ericnorrwing.weatherboy.handler.exception;

public class ResponseException extends RuntimeException{
    private final String detail;

    public ResponseException(String message, String detail) {
        super(message);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

}
