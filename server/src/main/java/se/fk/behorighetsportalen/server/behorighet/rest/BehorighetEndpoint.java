package se.fk.behorighetsportalen.server.behorighet.rest;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

@Path("/v1/behorighet")
public class BehorighetEndpoint {

    private static Logger logger = Logger.getLogger(BehorighetEndpoint.class);

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response skapaBehorighet() {
        logger.info("BehorighetEndpoint.skapaBehorighet()");

        try {
            return Response.ok().entity("Hello World!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }
}
