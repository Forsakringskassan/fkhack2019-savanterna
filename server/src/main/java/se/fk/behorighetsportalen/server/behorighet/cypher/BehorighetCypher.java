package se.fk.behorighetsportalen.server.behorighet.cypher;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.exception.ServerException;
import se.fk.behorighetsportalen.server.kategori.rest.Kategori;
import se.fk.behorighetsportalen.server.user.rest.User;

import java.util.*;

public class BehorighetCypher {

    public static String createBehorighet(Behorighet behorighet, Session session) throws ServerException {

        try(Transaction tx = session.beginTransaction()) {
            String query = "CREATE (b:Behörighet) " +
                    "SET b.id = {id} " +
                    "SET b.namn = {namn} " +
                    "SET b.beskrivning = {beskrivning} " +
                    "RETURN b.id as id";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", CypherUtil.generateId());
            parameters.put("namn", behorighet.getNamn());
            parameters.put("beskrivning", behorighet.getBeskrivning());
            StatementResult sr = tx.run(query, parameters);
            String id = sr.single().get("id").asString();

            setKategorier(id, behorighet.getKategorier(), tx);
            setGranskare(id, behorighet.getGranskare().getId(), tx);

            tx.success();
            return id;
        }
    }

    private static void setKategorier(String id, List<Kategori> kategorier, Transaction tx) {

        List<String> kIds = new ArrayList<>();
        for(Kategori k: kategorier) {
            kIds.add(k.getId());
        }

        String query = "MATCH(b:Behörighet {id: {id}}) " +
                "OPTIONAL MATCH(b)-[rel:TILLHÖR]->(:Kategori) " +
                "DELETE rel " +
                "WITH b " +
                "MATCH(k:Kategori) " +
                "WHERE k.id IN {kIds} " +
                "MERGE(b)-[:TILLHÖR]->(k)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("kIds", kIds);
        tx.run(query, parameters);
    }

    private static void setGranskare(String id, String userId, Transaction tx) {
        String query = "MATCH(b:Behörighet {id: {id}}) " +
                "OPTIONAL MATCH(b)-[rel:GRANSKAS_AV]->(:User) " +
                "DELETE rel " +
                "WITH b " +
                "MATCH(u:User {id: {userId}}) " +
                "MERGE(b)-[:GRANSKAS_AV]->(u)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("userId", userId);
        tx.run(query, parameters);
    }

    public static void mergeBehorighet(Behorighet behorighet, Session session) throws ServerException {

        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH (b:Behörighet {id: {id}}) " +
                    "SET b.beskrivning = {beskrivning} ";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", behorighet.getId());
            parameters.put("beskrivning", "En beskrivning");
            tx.run(query, parameters);

            setKategorier(behorighet.getId(), behorighet.getKategorier(), tx);
            setGranskare(behorighet.getId(), behorighet.getGranskare().getId(), tx);

            tx.success();
        }
    }

    public static void raderaBehorighet(String id, Session session) {
        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH (b:Behörighet {id: {id}}) " +
                    "DETACH DELETE b";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
            tx.run(query, parameters);
            tx.success();
        }
    }

    public static List<Behorighet> hamtaBehorigheter(Session session) {
        List<Behorighet> behorigheter = new ArrayList<>();
        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH(b:Behörighet) " +
                    "OPTIONAL MATCH(b)-[:GRANSKAS_AV]->(u:User) " +
                    "OPTIONAL MATCH(b)-[:TILLHÖR]->(k:Kategori) " +
                    "RETURN DISTINCT b as behorighet, collect(distinct k) as kategorier, u as granskare";
            StatementResult sr = tx.run(query);
            behorigheter = resultsToListBehorighet(sr);
            tx.success();
        }

        return behorigheter;
    }

    public static List<Behorighet> hamtaBehorigheter(List<String> ids, Transaction tx) {
        List<Behorighet> behorigheter = new ArrayList<>();
        if(ids.size() > 0) {
            String query = "MATCH(b:Behörighet) " +
                    "WHERE b.id IN {ids} " +
                    "OPTIONAL MATCH(b)-[:GRANSKAS_AV]->(u:User) " +
                    "OPTIONAL MATCH(b)-[:TILLHÖR]->(k:Kategori) " +
                    "RETURN DISTINCT b as behorighet, collect(distinct k) as kategorier, u as granskare";
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ids", ids);
            StatementResult sr = tx.run(query, parameters);
            behorigheter = resultsToListBehorighet(sr);
        }
        return behorigheter;
    }

    public static List<Behorighet> resultsToListBehorighet(StatementResult sr) {
        List<Behorighet> behorigheter = new ArrayList<>();
        while (sr.hasNext()) {
            Record record = sr.next();
            Value behorighet = record.get("behorighet");

            Behorighet b = new Behorighet();
            b.setId(behorighet.get("id").asString());
            b.setNamn(behorighet.get("namn").asString());
            b.setBeskrivning(behorighet.get("beskrivning").asString());
            b.setKategorier(new ArrayList<>());

            if(!record.get("granskare").isNull()) {
                Value granskare = record.get("granskare");
                User u = new User();
                u.setId(granskare.get("id").asString());
                u.setNamn(granskare.get("namn").asString());
                b.setGranskare(u);
            }

            if(!record.get("kategorier").isEmpty()) {
                List<Object> values = record.get("kategorier").asList();
                for(Object o: values) {
                    Node v = (Node) o;
                    Kategori k = new Kategori();
                    k.setId(v.get("id").asString());
                    k.setNamn(v.get("namn").asString());
                    b.getKategorier().add(k);
                }
            }

            behorigheter.add(b);
        }

        return behorigheter;
    }

    public static List<Behorighet> searchBehorighet(String input, Session session) {
        List<Behorighet> behorigheter = new ArrayList<>();
        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH (b:Behörighet) " +
                    "WHERE b.namn CONTAINS {input} " +
                    "OR b.beskrivning CONTAINS {input} " +
                    "RETURN COLLECT(DISTINCT b.id) as ids";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("input", input);
            StatementResult sr = tx.run(query, parameters);
            tx.success();
            Record record = sr.single();
            if(!record.get("ids").isEmpty()) {
                List<String> ids = record.get("ids").asList(Values.ofString());
                behorigheter = hamtaBehorigheter(ids, tx);
            }
        }

        return behorigheter;
    }

    public static Behorighet hamtaBehorighet(String b, Transaction tx) {
        List<String> ids = Arrays.asList(b);
        List<Behorighet> behorigheter = hamtaBehorigheter(ids, tx);
        return behorigheter.size() > 0 ? behorigheter.get(0) : null;
    }
}
