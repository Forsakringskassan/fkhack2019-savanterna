package se.fk.behorighetsportalen.server.kategori.cypher;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.kategori.rest.Kategori;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriCypher {

    public static void createKategori(Kategori kategori, Session session) {
        String cypher = "CREATE(k:Kategori) " +
                "SET k.namn = {namn} " +
                "SET k.id = {id} " +
                "RETURN k.id";

        Map parameters = new HashMap<String, Object>();
        parameters.put("namn", kategori.getNamn());
        parameters.put("id", CypherUtil.generateId());
        session.run(cypher, parameters);
    }

    public static List<Behorighet> getBehorigheter(String id, Session session) {
        List<Behorighet> behorigheter = new ArrayList<>();
        try(Transaction tx = session.beginTransaction()) {
            String query = "MATCH(k:Kategori {id: {id}})<-[:TILLHÖR]-(b:Behörighet) " +
                    "RETURN COLLECT(DISTINCT b.id) as ids";

            Map parameters = new HashMap<String, Object>();
            parameters.put("id", id);
            StatementResult sr = tx.run(query, parameters);
            Record record = sr.single();

            if(!record.get("ids").isNull()) {
                List<String> ids = record.get("ids").asList(Values.ofString());
                behorigheter = BehorighetCypher.hamtaBehorigheter(ids, tx);
            }

            tx.success();
        }
        return behorigheter;
    }

    public static List getKategorier(Session session) {
        List<Kategori> kategorier = new ArrayList<>();
        String query = "MATCH(k:Kategori) RETURN DISTINCT k";
        StatementResult sr = session.run(query);
        while(sr.hasNext()) {
            Record record = sr.next();
            Node k = record.get("k").asNode();

            Kategori kategori = new Kategori();
            kategori.setNamn(k.get("namn").asString());
            kategori.setId(k.get("id").asString());
            kategorier.add(kategori);
        }

        return kategorier;
    }
}
