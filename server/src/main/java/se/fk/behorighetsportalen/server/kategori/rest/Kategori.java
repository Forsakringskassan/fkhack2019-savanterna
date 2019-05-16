package se.fk.behorighetsportalen.server.kategori.rest;

public class Kategori {

    private String namn;
    private String id;

    public Kategori(String namn, String Id) {
        this.namn = namn;
        this.id = Id;
    }

    public String getid(){ return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamn(){ return namn; }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public Kategori() {
       super();
    }

}
