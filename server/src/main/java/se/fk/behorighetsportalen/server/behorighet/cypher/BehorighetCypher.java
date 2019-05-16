package se.fk.behorighetsportalen.server.behorighet.cypher;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.exception.ServerException;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BehorighetCypher {

    //TODO: Inparameter behorighet
    public static String createBehorighet(Session session) throws ServerException {

        try(Transaction tx = session.beginTransaction()) {
            String query = "CREATE (b:Behörighet) " +
                    "SET b.id = {id} " +
                    "SET b.namn = {namn} " +
                    "SET b.beskrivning = {beskrivning} " +
                    "RETURN b.id as id";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", CypherUtil.generateId());
            parameters.put("namn", "TEST");
            parameters.put("beskrivning", "En beskrivning");
            StatementResult sr = tx.run(query, parameters);
            String id = sr.single().get("id").asString();

            setKategorier(id, tx);

            tx.success();
            return id;
        }
    }

    private static void setKategorier(String id, Transaction tx) {
        String query = "MATCH(b:Behörighet {id: {id}}) " +
                "MATCH(b)-[rel:TILLHÖR]->(:Kategori) " +
                "DELETE rel " +
                "MATCH(k:Kategori) " +
                "WHERE k.id IN {kIds} " +
                "MERGE(b)-[:TILLÖR]->(k)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("kIds", new ArrayList<String>());
        tx.run(query, parameters);
    }

    //TODO: Inparameter behorighet
    public static void mergeBehorighet(Session session) throws ServerException {

        try(Transaction tx = session.beginTransaction()) {

            String id = "TODO";

            String query = "MATCH (b:Behörighet {id: {id}}) " +
                    "SET b.beskrivning = {beskrivning} ";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
            parameters.put("beskrivning", "En beskrivning");
            tx.run(query, parameters);

            setKategorier(id, tx);

            tx.success();
        }
    }
}
