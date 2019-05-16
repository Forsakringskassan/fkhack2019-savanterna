package se.fk.behorighetsportalen.server.behorighet.rest;

import se.fk.behorighetsportalen.server.user.rest.User;

import java.util.Arrays;
import java.util.List;

public class Behorighet {
    private String id;
    private String namn;
    private List<String> kategorier;
    private String beskrivning;
    private User granskare;

    public Behorighet() { super(); }

    public Behorighet(String id, String namn, List<String> kategorier, String beskrivning, User granskare) {
        this.id = id;
        this.namn = namn;
        this.kategorier = kategorier;
        this.beskrivning = beskrivning;
        this.granskare = granskare;
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

    public List<String> getKategorier() {
        return kategorier;
    }

    public void setKategorier(List<String> kategorier) {
        this.kategorier = kategorier;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }

    public User getGranskare() {
        return granskare;
    }

    public void setGranskare(User granskare) {
        this.granskare = granskare;
    }

    @Override
    public String toString() {
        return "Behorighet{" +
                "id='" + id + '\'' +
                ", namn='" + namn + '\'' +
                ", kategorier=" + kategorier +
                ", beskrivning='" + beskrivning + '\'' +
                ", granskare=" + granskare +
                '}';
    }
}