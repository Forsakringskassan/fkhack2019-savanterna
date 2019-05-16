package se.fk.behorighetsportalen.server.user.rest;

import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

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
}
