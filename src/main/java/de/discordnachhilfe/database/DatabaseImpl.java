package de.discordnachhilfe.database;

import de.discordnachhilfe.core.Main;
import io.github.cdimascio.dotenv.Dotenv;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DatabaseImpl implements Database {

    public static final Dotenv dotenv = Main.dotenv;
    private static DatabaseImpl databaseImpl;
    private Connection connection;
    private PreparedStatement createUserStatement;

    private DatabaseImpl() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:nachhilfe.db");
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS `members` (" +
                    "`guild_id` VARCHAR NOT NULL," +
                    "`member_id` VARCHAR NOT NULL," +
                    "`member_rank` INT DEFAULT 0," +
                    "`member_joined` DATETIME NOT NULL DEFAULT 'CURRENT_TIMESTAMP()'," +
                    "`member_activated` BOOLEAN DEFAULT true," +
                    "PRIMARY KEY (`guild_id`,`member_id`));");
            createUserStatement = connection.prepareStatement("INSERT INTO members (\"guild_id\", \"member_id\") VALUES (?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getDatabase() {
        if (databaseImpl == null) {
            databaseImpl = new DatabaseImpl();
        }
        return databaseImpl;
    }

    public void createUser(String guildId, String userId) {
        try {
            createUserStatement.clearParameters();
            createUserStatement.setString(1, guildId);
            createUserStatement.setString(2, userId);
            createUserStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String guildId, String userId) {
        try {
            connection.createStatement().execute("UPDATE members SET member_activated = false WHERE guild_id = \"" + guildId + "\" AND user_id = \"" + userId + "\";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRank(String guildId, String userId, int rank) {

    }

    public int getRank(String guildId, String userId) {
        return 0;
    }

    public LocalDateTime getJoinTime(String guildId, String userId) {
        return null;
    }
}
