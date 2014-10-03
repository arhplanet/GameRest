
package no.arhplanet.game.resources;

import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import no.arhplanet.game.dao.PlayerDaoImpl;
import no.arhplanet.game.models.Player;

@Path("/player")
public class PlayerResource {

    private PlayerDaoImpl playerDao;

    public PlayerResource() {

    }

    @Inject
    public PlayerResource(PlayerDaoImpl playerDao) {
        this.playerDao = playerDao;
    }

    @GET
    @Path("info")
    @Produces("application/json")
    public Response info(@QueryParam("player_id") String playerId) throws SQLException {
        Player p = new Player();
        p.setEmail("test");
        p.setNick("test");
        p.setPasswordHash("test");
        p.setLastActive(new Date());
        playerDao.save(p);
        return Response.ok(p).build();
    }

    @POST
    @Produces("application/json")
    public Response register(@QueryParam("email_adress") String email,
                             @QueryParam("nick") String nick,
                             @QueryParam("passwordHash") String passwordHash) throws SQLException{
        Player p = new Player();
        p.setEmail(email);
        p.setNick(nick);
        p.setPasswordHash(passwordHash);
        p.setLastActive(new Date());
        playerDao.save(p);
        return Response.ok(p).build();
    }

    @DELETE
    @Produces("application/json")
    public Response delete(@QueryParam("player_id") String playerId,
                           @QueryParam("passwordHash") String passwordHash) {
        return Response.ok("resetGame").build();
    }
}
