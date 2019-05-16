package se.fk.behorighetsportalen.server.kategori.rest;

public class Kategori {

    private String namn;
    private String id;

    public Kategori(){ super(); }

    public Kategori(String namn, String id) {
        this.namn = namn;
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Kategori{" +
                "id=" + id +
                ", namn=" + namn +
                "}";
    }

}
