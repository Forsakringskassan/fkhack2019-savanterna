package se.fk.behorighetsportalen.server.ansokan.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.user.rest.UserEndpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

@Path("v1/ansokan")
public class AnsokanEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @POST
    @Path("/skapa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser() {
        logger.info("AnsokanEndpoint.create()");
    //Todo: Skapa ansokan i DB
        try {
            return Response.ok().entity("").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @PUT
    @Path("/uppdatera/{ansokanId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAnsokan(@PathParam("ansokanId") String ansokanId){
        //Todo: Skapa ansokan i DB
        try {
            return Response.ok().entity("").build();
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
