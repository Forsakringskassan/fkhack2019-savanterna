package se.fk.behorighetsportalen.server.behorighet.cypher;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.exception.ServerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static void setKategorier(String id, List<String> kategorier, Transaction tx) {
        String query = "MATCH(b:Behörighet {id: {id}}) " +
                "OPTIONAL MATCH(b)-[rel:TILLHÖR]->(:Kategori) " +
                "DELETE rel " +
                "WITH b " +
                "MATCH(k:Kategori) " +
                "WHERE k.id IN {kIds} " +
                "MERGE(b)-[:TILLÖR]->(k)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("kIds", kategorier);
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
}
