package se.fk.behorighetsportalen.server.database;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;
import se.fk.behorighetsportalen.server.exception.ServerException;

import java.net.HttpURLConnection;
import java.util.Map;

public class DatabaseConnector implements AutoCloseable {

    private Driver driver = null;
    private static DatabaseConnector instance;

    private DatabaseConnector() {}

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }

        return instance;
    }

    public static void setDriver(String uri, String user, String password) {
        getInstance().driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public static void setDriver(Driver driver) {
        getInstance().driver = driver;
    }

    public static StatementResult runQuery(String query, Map<String, Object> parameters) throws ServerException {
        //TODO: Skall inte utföras på detta sätt!
        if(getInstance().driver == null) {
            setDriver("bolt://localhost:7687", "neo4j", "admin");
        }

        try (Session session = getInstance().driver.session()) {
            return session.writeTransaction(tx -> {
                StatementResult result = tx.run(query, parameters);
                return result;
            });
        } catch (ServiceUnavailableException e) {
            throw new ServerException("Database unavailable", HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
    }

    @Override
    public void close() throws Exception {
        if (driver != null) {
            driver.close();
        }
    }
}

