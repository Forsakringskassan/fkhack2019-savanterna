package se.fk.behorighetsportalen.server.kategori.cypher;

import org.neo4j.driver.v1.Session;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.kategori.rest.Kategori;

import java.util.HashMap;
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
}
