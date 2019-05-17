package se.fk.behorighetsportalen.server.ansokan.rest;

import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;

public class Ansokan {
    public enum Status {
        PÅBÖRJAD, BEVILJAD, NEKAD;
    }
    private String id;
    private Behorighet behorighet;
    private String userId;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Behorighet getBehorighet() {
        return behorighet;
    }

    public void setBehorighet(Behorighet behorighet) {
        this.behorighet = behorighet;
    }

    @Override
    public String toString() {
        return "Ansokan{" +
                "id='" + id + '\'' +
                ", behorighet='" + behorighet + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
