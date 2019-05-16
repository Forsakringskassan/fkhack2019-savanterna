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


    public static Session getSession() {
        if(getInstance().driver == null) {
            setDriver("bolt://localhost:7687", "neo4j", "admin");
        }
        return getInstance().driver.session();
    }

    @Override
    public void close() throws Exception {
        if (driver != null) {
            driver.close();
        }
    }
}

