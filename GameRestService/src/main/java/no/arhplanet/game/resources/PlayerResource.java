package no.arhplanet.game.resources;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.arhplanet.game.dao.PlayerDaoImpl;
import no.arhplanet.game.models.Player;

@Path("/player")
public class PlayerResource {

    final static Logger logger = LoggerFactory.getLogger(PlayerResource.class);

    private PlayerDaoImpl playerDao;

    private PlayerResource() {}

    @Inject
    public PlayerResource(PlayerDaoImpl playerDao) {
        this.playerDao = playerDao;
    }

    @GET
    @Path("info")
    @Produces("application/json")
    public Response info(@QueryParam("player_id") Long playerId) throws SQLException {
        logger.info("info about player :" + playerId);
        Player p = playerDao.getById(playerId);
        if (p == null) {
            return Response.status(404).build();
        }
        return Response.ok(p).build();
    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public Response getAll(@QueryParam("player_id") String playerId) throws SQLException {
        return Response.ok(playerDao.getAll()).build();
    }

    @POST
    @Path("register")
    @Produces("application/json")
    public Response register(@QueryParam("email") String email,
                             @QueryParam("nick") String nick) throws SQLException{
        //if (playerDao.allreadyRegistered(email)) {
            //return Response.ok("player already registered").build();
        //}
        logger.info("registration of player :" + nick + " (" + email + ")");
        Player p = new Player(null, email, nick, new Date());
        UUID uuid = UUID.randomUUID();
        String genPassword =  String.valueOf(uuid);
        p.setPasswordHash(genPassword);
        playerDao.save(p);
        logger.info("player saved " + p.getNick());
        logger.info("password generated " + genPassword);
        return Response.ok(p).build();
    }

    @DELETE
    @Produces("application/json")
    public Response delete(@QueryParam("player_id") String playerId,
                           @QueryParam("password_hash") String passwordHash) {
        return Response.status(501).build();
    }
}
