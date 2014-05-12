/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author dawo1
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
                System.out.println("Connection established.");

                try {
                    stmt = connection.createStatement();
                    System.out.println("Created JDBC Statement object");

                    stmt.executeUpdate("UPDATE EMPINST.EMPLOYEE SET PROF_JOB_RESPONSIBILITIES = '" + Taetigkeit + "' WHERE PROF_KEY='" + ID + "'");

                    System.out.println("Created JDBC ResultSet object");

                    //rs_insert.close();
                    System.out.println("Closed JDBC ResultSet");

                    // Close the Statement
                    stmt.close();
                    System.out.println("Closed JDBC Statement");

                    // Connection must be on a unit-of-work boundary to allow close
                    connection.commit();
                    System.out.println("Transaction committed");

                    // Close the connection
                    connection.close();
                    System.out.println("Disconnected from data source");

                } catch (SQLException e) {
                }

            }
        }

    }

}
