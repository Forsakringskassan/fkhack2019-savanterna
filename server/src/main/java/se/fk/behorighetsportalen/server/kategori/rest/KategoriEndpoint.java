package se.fk.behorighetsportalen.server.kategori.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.kategori.cypher.KategoriCypher;
import se.fk.behorighetsportalen.server.user.rest.UserEndpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Path("/v1/kategori")
public class KategoriEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @POST
    @Path("/skapa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response skapaKategori(Kategori kategori) {
        logger.info("KategoriEndpoint.skapaKategori()");
        try {
            KategoriCypher.createKategori(kategori, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/hamta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaKategorier() {
        logger.info("KategoriEndpoint.hamtaKategorier()");

        try {
            List kategorier=new ArrayList <Kategori> ();
            kategorier.add(new Kategori("Systemutvecklare BI","1"));
            kategorier.add(new Kategori("Projektledare","2"));
            kategorier.add(new Kategori("IT-Tekniker","3"));

            return Response.ok().entity(kategorier).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/{id}/behorigheter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaBehorigheter(@PathParam("id") String id) {
        logger.info("KategoriEndpoint.hamtaBehorigheter()");

        try {
            List<Behorighet> behorigheter = KategoriCypher.getBehorigheter(id, DatabaseConnector.getSession());
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }
}
