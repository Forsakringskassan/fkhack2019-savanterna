package se.fk.behorighetsportalen.server.user.cypher;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.ansokan.rest.Ansokan;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.user.rest.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCypher {

    public static List<Ansokan> getAnsokningar(String userId, Session session) {
        List<Ansokan> ansokningar = new ArrayList<>();

        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH(u:User {id: {id}})-[:SKAPAT]->(a:Ansökan) " +
                    "MATCH(a)-[:ANSÖKER]->(b:Behörighet) " +
                    "RETURN DISTINCT a, b.id";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", userId);

            StatementResult sr = tx.run(query, parameters);
            while(sr.hasNext()) {
                Record record = sr.next();
                String b = record.get("b.id").asString();
                Behorighet behorighet = BehorighetCypher.hamtaBehorighet(b, tx);

                Node a = record.get("a").asNode();
                Ansokan ansokan = new Ansokan();
                ansokan.setId(a.get("id").asString());
                ansokan.setBehorighet(behorighet);
                ansokan.setUserId(userId);
                ansokan.setStatus(CypherUtil.convertToStatus(a.get("status").asInt()));
                ansokningar.add(ansokan);
            }

            tx.success();
        }

        return ansokningar;
    }

    public static List<Behorighet> getBehorigheter(String userId, Session session) {
        List<Behorighet> behorigheter = new ArrayList<>();

        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH(u:User {id: {id}})-[:HAR_BEHÖRIGHET]->(b:Behörighet) " +
                    "RETURN COLLECT( DISTINCT b.id) as ids";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", userId);

            StatementResult sr = tx.run(query, parameters);
            if(sr.hasNext()) {
                Record record = sr.next();
                List<String> ids = record.get("ids").asList(Values.ofString());
                behorigheter = BehorighetCypher.hamtaBehorigheter(ids, tx);
            }

            tx.success();
        }

        return behorigheter;
    }

    public static void createUser(User user, Session session) {
        String query = "CREATE(u:User) " +
                "SET u.id = {id} " +
                "SET u.namn = {namn} ";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("namn", user.getNamn());

        session.run(query, parameters);
    }
}
