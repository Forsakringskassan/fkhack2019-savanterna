package se.fk.behorighetsportalen.server.user.rest;

import org.jboss.logging.Logger;
import se.fk.behorighetsportalen.server.CypherUtil;
import se.fk.behorighetsportalen.server.ansokan.rest.Ansokan;
import se.fk.behorighetsportalen.server.behorighet.cypher.BehorighetCypher;
import se.fk.behorighetsportalen.server.behorighet.rest.Behorighet;
import se.fk.behorighetsportalen.server.behorighet.rest.BehorighetEndpoint;
import se.fk.behorighetsportalen.server.database.DatabaseConnector;
import se.fk.behorighetsportalen.server.kategori.rest.Kategori;
import se.fk.behorighetsportalen.server.user.cypher.UserCypher;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.util.List;

@Path("/v1/user")
public class UserEndpoint {

    private static Logger logger = Logger.getLogger(UserEndpoint.class);

    @POST
    @Path("/skapa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        logger.info("UserEndpoint.createUser()");
        try {
            UserCypher.createUser(user, DatabaseConnector.getSession());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/{userId}/behorigheter/hamta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaBehorigheter(@PathParam("userId") String userId) {
        List<Behorighet> behorigheter = UserCypher.getBehorigheter(userId, DatabaseConnector.getSession());
        try {
            return Response.ok().entity(behorigheter).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

    @GET
    @Path("/{userId}/ansokningar/hamta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hamtaAnsokningar(@PathParam("userId") String userId){
        try {
            List<Ansokan> ansokningar = UserCypher.getAnsokningar(userId, DatabaseConnector.getSession());
            return Response.ok().entity(ansokningar).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build();
        }
    }

}
