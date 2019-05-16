package se.fk.behorighetsportalen.server.behorighet.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.user.rest.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/v1/behorighet")
public class BehorighetEndpoint {

    private static Logger logger = Logger.getLogger(BehorighetEndpoint.class);

    @POST
    @Path("/skapa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response skapaBehorighet(Behorighet behorighet) {
        logger.info("BehorighetEndpoint.skapaBehorighet()");

        try {
            logger.info("Skapar behörighet: " + behorighet.toString());

            BehorighetCypher.createBehorighet(behorighet, DatabaseConnector.getSession());

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @POST
    @Path("/uppdatera")
    @Produces(MediaType.APPLICATION_JSON)
    public Response uppdateraBehorighet(Behorighet behorighet) {
        logger.info("BehorighetEndpoint.uppdateraBehorighet()");

        try {
            logger.info("Uppdaterar behörighet: " + behorighet.toString());
            BehorighetCypher.mergeBehorighet(behorighet, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @POST
    @Path("/radera/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response raderaBehorighet(@PathParam("id") String id) {
        logger.info("BehorighetEndpoint.raderaBehorighet()");

        //TODO: DELETE FROM DB WHERE "ID" = id
        try {
            logger.info("Raderar behörighet med id: " + id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/hamta")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hamtaBehorighet() {
        logger.info("BehorighetEndpoint.hamtaBehorighet()");

        //TODO: SELECT * FROM BEHORIGHETER
        List<Behorighet> behorigheter = new ArrayList<>();
        behorigheter.add(new Behorighet("1", "GStashUser", Arrays.asList("Stash", "Systemutvecklare"), "Läsrättigheter för Stash", new User("1", "Kalle Karlsson")));
        behorigheter.add(new Behorighet("2", "GConfluenceUser", Arrays.asList("Confluence", "Systemutvecklare", "IT-Samordnare", "Testare"), "Läsrättigheter för Confluence", new User("2", "Anna Jansson")));
        behorigheter.add(new Behorighet("3", "GWin10SuperUser", Arrays.asList("Systemutvecklare"), "Superuser för Windows 10", new User("1", "Kalle Karlsson")));
        behorigheter.add(new Behorighet("4", "GSkypeUser", Arrays.asList("Skype", "Systemutvecklare", "IT-Samordnare", "Testare"), "Rättigheter för att använda Skype", new User("3", "Anders Andersson")));

        try {
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/sok")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sokBehorighet(@QueryParam("input") String input) {
        logger.info("BehorighetEndpoint.sokBehorighet()");

        List<Behorighet> behorigheter = new ArrayList<>();
        behorigheter.add(new Behorighet("2", "GConfluenceUser", Arrays.asList("Confluence", "Systemutvecklare", "IT-Samordnare", "Testare"), "Läsrättigheter för Confluence", new User("2", "Anna Jansson")));
        behorigheter.add(new Behorighet("3", "GWin10SuperUser", Arrays.asList("Test"), "Superuser för Windows 10", new User("1", "Kalle Karlsson")));
        try {
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

}
