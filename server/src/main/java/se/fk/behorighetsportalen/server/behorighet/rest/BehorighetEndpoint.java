package se.fk.behorighetsportalen.server.behorighet.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.kategori.rest.Kategori;
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
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
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

    @DELETE
    @Path("/radera/{id}")
    public Response raderaBehorighet(@PathParam("id") String id) {
        logger.info("BehorighetEndpoint.raderaBehorighet()");
        try {
            logger.info("Raderar behörighet med id: " + id);
            BehorighetCypher.raderaBehorighet(id, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/hamta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaBehorighet() {
        logger.info("BehorighetEndpoint.hamtaBehorighet()");
        try {
            List<Behorighet> behorigheter = BehorighetCypher.hamtaBehorigheter(DatabaseConnector.getSession());
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/sok")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sokBehorighet(@QueryParam("input") String input) {
        logger.info("BehorighetEndpoint.sokBehorighet()");

        try {
            List<Behorighet> behorigheter = BehorighetCypher.searchBehorighet(input, DatabaseConnector.getSession());
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

}
