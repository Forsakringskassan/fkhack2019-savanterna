package se.fk.behorighetsportalen.server.user.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.behorighet.rest.BehorighetEndpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/v1/user")
public class UserEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser() {
        logger.info("UserEndpoint.createUser()");

        try {
            return Response.ok().entity("Hello World!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    //Queryparam
    //Pathparam

    @GET
    @Path("behorigheter/hamta/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaBehorigheter(@PathParam("id") String userId) {
        List<Behorighet> behorigheter = new ArrayList<Behorighet>();

        User user = new User(UUID.randomUUID().toString(), "Roger Pontare" );
        String[] kategorier = {"Första", "Tredje"};

        Behorighet behorighet = new Behorighet(UUID.randomUUID().toString(),"Systemaccess" , kategorier , "Ger access till systemet.", user);
        behorighet.setId(UUID.randomUUID().toString());

        behorigheter.add(behorighet);

        try {
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }

    }


}
