package se.fk.behorighetsportalen.server.ansokan.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.ansokan.cypher.AnsokanCypher;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.exception.ServerException;
import se.fk.behorighetsportalen.server.user.rest.UserEndpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

@Path("/v1/ansokan")
public class AnsokanEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @POST
    @Path("/skapa/{userId}/{behorighetId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response skapa(@PathParam("userId") String userId, @PathParam("behorighetId") String behorighetId) {
        logger.info("AnsokanEndpoint.skapa()");
        try {
            AnsokanCypher.skapaAnsokan(userId, behorighetId, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch(ServerException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @POST
    @Path("/uppdatera/{ansokanId}/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAnsokan(@PathParam("ansokanId") String ansokanId, @PathParam("status") int status){
        try {
            AnsokanCypher.uppdateraAnsokan(ansokanId, status, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @DELETE
    @Path("/radera/{ansokanId}")
    public Response raderaAnsokan(@PathParam("ansokanId") String ansokanId){
        //Todo: Radera ansokan ifrån DB
        try{
            return Response.ok().entity("").build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }
}
