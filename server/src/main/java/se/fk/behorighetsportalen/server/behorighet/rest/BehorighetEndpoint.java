package se.fk.behorighetsportalen.server.behorighet.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.user.rest.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;

@Path("/v1/behorighet")
public class BehorighetEndpoint {

    private static Logger logger = Logger.getLogger(BehorighetEndpoint.class);

    @POST
    @Path("/skapa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response skapaBehorighet(Behorighet behorighet) {
        logger.info("BehorighetEndpoint.skapaBehorighet()");

        String id = Integer.toString(behorighet.getNamn().hashCode());
        behorighet.setId(id);

        //TODO: CREATE BEHORIGHET IN DB FROM behorighet
        try {
            logger.info("Skapar behörighet: " + behorighet.toString());
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

        //TODO: UPDATE BEHORIGHET IN DB TO behorighet WHERE behorighet.getId() = id
        try {
            logger.info("Uppdaterar behörighet: " + behorighet.toString());
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
        Behorighet[] behorigheter = new Behorighet[4];
        behorigheter[0] = new Behorighet("1", "GStashUser", new String[]{"Stash", "Systemutvecklare"}, "Läsrättigheter för Stash", new User("1", "Kalle Karlsson"));
        behorigheter[1] = new Behorighet("2", "GConfluenceUser", new String[]{"Confluence", "Systemutvecklare", "IT-Samordnare", "Testare"}, "Läsrättigheter för Confluence", new User("2", "Anna Jansson"));
        behorigheter[2] = new Behorighet("3", "GWin10SuperUser", new String[]{"Systemutvecklare"}, "Superuser för Windows 10", new User("1", "Kalle Karlsson"));
        behorigheter[3] = new Behorighet("4", "GSkypeUser", new String[]{"Skype", "Systemutvecklare", "IT-Samordnare", "Testare"}, "Rättigheter för att använda Skype", new User("3", "Anders Andersson"));
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

        Behorighet[] behorigheter = new Behorighet[2];
        behorigheter[0] = new Behorighet("2", "GConfluenceUser", new String[]{"Confluence", "Systemutvecklare", "IT-Samordnare", "Testare"}, "Läsrättigheter för Confluence", new User("2", "Anna Jansson"));
        behorigheter[1] = new Behorighet("3", "GWin10SuperUser", new String[]{"Systemutvecklare"}, "Superuser för Windows 10", new User("1", "Kalle Karlsson"));
        try {
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

}
