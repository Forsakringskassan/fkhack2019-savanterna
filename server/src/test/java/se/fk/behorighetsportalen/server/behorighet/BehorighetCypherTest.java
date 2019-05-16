package se.fk.behorighetsportalen.server.behorighet;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.harness.junit.Neo4jRule;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;

public class BehorighetCypherTest {

    @Rule
    public Neo4jRule neo4j = new Neo4jRule();

    @Test
    public void createBehorighetTest() throws Throwable {
        try (Driver driver = GraphDatabase.driver(neo4j.boltURI(), Config.build().toConfig())) {
            DatabaseConnector.getInstance();
            DatabaseConnector.setDriver(driver);
            String id = BehorighetCypher.createBehorighet(DatabaseConnector.getSession());
            Assert.assertNotNull(id);
        }
    }
}
