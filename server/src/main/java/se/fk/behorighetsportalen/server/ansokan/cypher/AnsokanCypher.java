package se.fk.behorighetsportalen.server.ansokan.cypher;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.ansokan.rest.Ansokan;
import se.fk.behorighetsportalen.server.exception.ServerException;

import javax.servlet.http.HttpServlet;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class AnsokanCypher {

    public static void skapaAnsokan(String userId, String behorighetId, Session session) throws ServerException {
        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH(u:User {id: {userId}}) " +
                    "MATCH(b:Behörighet {id: {behorighetId}}) " +
                    "MATCH(b)-[:GRANSKAS_AV]->(u2:User)" +
                    "RETURN u.id as user, b.id as beh, u2.id as gran";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("behorighetId", behorighetId);
            StatementResult sr = tx.run(query, parameters);
            if(sr.hasNext()) {
                Record record = sr.single();
                if(!record.get("user").isNull() && !record.get("beh").isNull() && !record.get("gran").isNull()) {
                    String user = record.get("user").asString();
                    String beh = record.get("beh").asString();

                    String aId = skapaAnsokan(user, beh, tx);

                    if(aId == null) {
                        throw new ServerException("Misslyckades skapa ansökan", HttpURLConnection.HTTP_BAD_REQUEST);
                    }
                }
            }

            tx.success();
        }
    }

    private static String skapaAnsokan(String userId, String behorighetId, Transaction tx) {
        String query = "CREATE(a:Ansökan {id: {aId}}) " +
                "SET a.status = {status}" +
                "WITH a " +
                "MATCH(u:User { id: {uId}}) " +
                "MATCH(b:Behörighet {id: {bId}}) " +
                "MERGE(u)-[:SKAPAT]->(a) " +
                "MERGE(a)-[:ANSÖKER]->(b) " +
                "RETURN a.id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("aId", CypherUtil.generateId());
        parameters.put("status", Ansokan.Status.PÅBÖRJAD);
        parameters.put("uId", userId);
        parameters.put("bId", behorighetId);

        StatementResult sr = tx.run(query, parameters);
        Record record = sr.single();
        if(!record.get("id").isNull()) {
            return record.get("id").asString();
        }
        return null;
    }

    public static void uppdateraAnsokan(String ansokanId, int status, Session session) {
        String query = "MATCH(a:Ansökan {id : {id}}) " +
                "SET a.status = {status} ";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", ansokanId);
        parameters.put("status", status);
        session.run(query, parameters);
    }
}
