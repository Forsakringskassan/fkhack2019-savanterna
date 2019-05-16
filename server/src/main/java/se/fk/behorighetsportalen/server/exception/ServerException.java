package se.fk.behorighetsportalen.server.exception;

import java.net.HttpURLConnection;

public class ServerException extends Exception{

    private Integer status = HttpURLConnection.HTTP_BAD_REQUEST;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
