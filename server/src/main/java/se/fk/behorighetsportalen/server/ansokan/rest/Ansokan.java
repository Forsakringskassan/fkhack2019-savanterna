package se.fk.behorighetsportalen.server.ansokan.rest;

public class Ansokan {
    public enum Status {
        PÅBÖRJAD, BEVILJAD, NEKAD;
    }
    private String id;
    private String behorighetsId;
    private String userId;
    private String granskarId;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBehorighetsId() {
        return behorighetsId;
    }

    public void setBehorighetsId(String behorighetsId) {
        this.behorighetsId = behorighetsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGranskarId() {
        return granskarId;
    }

    public void setGranskarId(String granskarId) {
        this.granskarId = granskarId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ansokan{" +
                "id='" + id + '\'' +
                ", behorighetsId='" + behorighetsId + '\'' +
                ", userId='" + userId + '\'' +
                ", granskarId='" + granskarId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ansokan ansokan = (Ansokan) o;
        return id.equals(ansokan.id) &&
                behorighetsId.equals(ansokan.behorighetsId) &&
                userId.equals(ansokan.userId) &&
                granskarId.equals(ansokan.granskarId);
    }

}
