package se.fk.behorighetsportalen.server.user.rest;

public class User {

    private String id;
    private String namn;

    public User() { super(); }

    public User(String id, String namn) {
        this.id = id;
        this.namn = namn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", namn='" + namn + '\'' +
                '}';
    }
}
