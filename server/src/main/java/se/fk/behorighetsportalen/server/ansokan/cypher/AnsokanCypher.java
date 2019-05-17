package se.fk.behorighetsportalen.server.ansokan.cypher;

import org.jboss.logging.Logger;
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

    private static Logger logger = Logger.getLogger(AnsokanCypher.class);

    public static void skapaAnsokan(String userId, String behorighetId, Session session) throws ServerException {
        logger.info("AnsokanCypher.skapaAnsokan()");
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

                    skapaAnsokan(user, beh, tx);
                }
            }

            tx.success();
        }
    }

    private static void skapaAnsokan(String userId, String behorighetId, Transaction tx) {
        logger.info("AnsokanCypher.skapaAnsokan()");
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
        parameters.put("status", 0);
        parameters.put("uId", userId);
        parameters.put("bId", behorighetId);

        tx.run(query, parameters);
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
