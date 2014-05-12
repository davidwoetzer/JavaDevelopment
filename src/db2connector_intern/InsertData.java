package db2connector_intern;

import static db2connector_intern.Main.jdbcClassName;
import static db2connector_intern.Main.password;
import static db2connector_intern.Main.url;
import static db2connector_intern.Main.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Woetzer
 */
public class InsertData {

    Statement stmt;
    String taetigkeit;
    String prof_key;

    ResultSet rs_insert;

    Connection connection = null;

    public void insert(String ID, String Taetigkeit) {

        try {
            //Load class into memory
            Class.forName(jdbcClassName);
            //Establish connection
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (connection != null) {

                try {
                    stmt = connection.createStatement();

                    stmt.executeUpdate("UPDATE EMPINST.EMPLOYEE SET PROF_JOB_RESPONSIBILITIES = '" + Taetigkeit + "' WHERE PROF_KEY='" + ID + "'");

                    stmt.close();

                    connection.commit();

                    connection.close();

                } catch (SQLException e) {
                }

            }
        }

    }

}
