package no.arhplanet.game.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import no.arhplanet.game.models.Player;

public class PlayerDaoImpl extends AbstractDao<Player> {

    private static final String SIMPLE_PLAYER_QUERY = "SELECT * FROM player";

    @Override
    public List<? extends Player> getAll() {
        return null;
    }

    @Override
    public Player getById(String id) {
        return null;
    }

    public void save(Player p) throws SQLException{
        PreparedStatement ps =
                conn.prepareStatement( "INSERT INTO PLAYER VALUES( ?, ?, ? )" );
        ps.setString( 1, p.getEmail() );
        ps.setString( 2, p.getNick() );
        ps.setString( 3, p.getPasswordHash() );
        ps.executeUpdate();
    }
}
