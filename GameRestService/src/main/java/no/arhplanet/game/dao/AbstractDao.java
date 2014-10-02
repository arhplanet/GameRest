package no.arhplanet.game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {

    String dbUser = "kp";
    String dbPassword = "password";

    final String dbUrl = "jdbc:mysql://db.local:3306/gamerestservice";
    Connection conn = null;



    public AbstractDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  catch (ClassNotFoundException ef) {
            throw new RuntimeException(ef);
        } catch (IllegalAccessException ef) {
            throw new RuntimeException(ef);
        } catch (InstantiationException ef) {
            throw new RuntimeException(ef);
        }
    }

}
