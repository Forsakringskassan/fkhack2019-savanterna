package se.fk.behorighetsportalen.server.kategori.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.user.rest.UserEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

@Path("/kategori")
public class KategoriEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @GET
    @Produces("text/plain")
    public Response skapaKategori() {
        logger.info("KategoriEndpoint.skapaKategori()");

        try {
            return Response.ok().entity("Skapa kategori").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }
}
