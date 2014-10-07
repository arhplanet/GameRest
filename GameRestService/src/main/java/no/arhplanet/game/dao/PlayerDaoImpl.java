package no.arhplanet.game.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import no.arhplanet.game.models.Player;


public class PlayerDaoImpl extends AbstractDao<Player> {

    private static final String SIMPLE_PLAYER_QUERY = "SELECT * FROM player";

    @Override
    public List<? extends Player> getAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        Statement ps = conn.createStatement();
        ResultSet resultSet = ps.executeQuery(SIMPLE_PLAYER_QUERY);
        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String email = resultSet.getString("EMAIL");
            String nick = resultSet.getString("NICK");
            Date lastActive = resultSet.getDate("LAST_ACTIVE");
            players.add(mapPlayer(id, email, nick, lastActive));
        }
        return players;
    }

    private Player mapPlayer(Long id, String email, String nick, Date lastActive) {
        return new Player(id, email, nick, lastActive);
    }

    @Override
    public Player getById(Long id) throws SQLException {
        Statement ps = conn.createStatement();
        Player p = null;
        ResultSet resultSet = ps.executeQuery(SIMPLE_PLAYER_QUERY + " WHERE ID = " + id);
        while (resultSet.next()) {
            id = resultSet.getLong("ID");
            String email = resultSet.getString("EMAIL");
            String nick = resultSet.getString("NICK");
            Date lastActive = resultSet.getDate("LAST_ACTIVE");
            p = mapPlayer(id, email, nick, lastActive);
            break;
        }
        return p;
    }

    public void save(Player p) throws SQLException{
        if(p.getId() != null) {
            updatePlayer(p);
        } else {
            savePlayer(p);
        }
    }

    private void updatePlayer(Player p) throws SQLException {
        PreparedStatement ps = conn.prepareStatement( "UPDATE PLAYER SET EMAIL = ?, NICK = ? where ID = ?");
        ps.setString( 1, p.getEmail() );
        ps.setString( 2, p.getNick() );
        ps.setLong(3, p.getId());
        ps.executeUpdate();
    }

    private void savePlayer(Player p) throws SQLException {
        PreparedStatement ps = conn.prepareStatement( "INSERT INTO PLAYER (EMAIL, NICK, PASSWORDHASH) VALUES(?, ?, ? )", Statement.RETURN_GENERATED_KEYS);
        ps.setString( 1, p.getEmail() );
        ps.setString( 2, p.getNick() );
        ps.setString( 3, p.getPasswordHash() );
        ps.executeUpdate();
    }

    public boolean allreadyRegistered(String email) throws SQLException{
        Statement ps = conn.createStatement();
        Player p = null;
        ResultSet resultSet = ps.executeQuery(SIMPLE_PLAYER_QUERY + " WHERE EMAIL = " + email);
        while (resultSet.next()) {
            return true;
        }
        return false;
    }
}
