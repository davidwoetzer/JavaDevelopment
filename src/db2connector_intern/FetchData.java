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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dawo1
 */
public class FetchData {

    public static List<String> result_list;

    InsertData _insert = new InsertData();

    public void _fetchData() {

        //System.setOut(new PrintStream(new FileOutputStream("d:\\db2_con\\COMPLETE_SYS_OUT.txt")));
        ResultSet rs_result;
        ResultSet rs_insert;

        Statement stmt;
        String taetigkeit;
        String prof_key;

        Connection connection = null;
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

                    // Execute a query and generate a ResultSet instance
                    rs_result = stmt.executeQuery("SELECT PROF_KEY, PROF_VALUE FROM EMPINST.PROFILE_EXTENSIONS WHERE PROF_PROPERTY_ID='taetigkeit'");

                    System.out.println("Created JDBC ResultSet object");

                    result_list = new ArrayList<>();

                    // Print all of the employee numbers to standard output device
                    while (rs_result.next()) {

                        result_list.add(rs_result.getString(1)); //ID

                        result_list.add(rs_result.getString(2)); // Taetigkeit

                        System.out.println(rs_result.getString(1));

                        String check = rs_result.getString(2);

                        if (!rs_result.getString(2).equals("")) {
                            System.out.println(rs_result.getString(2));
                            _insert.insert(rs_result.getString(1), rs_result.getString(2));

                        }

                    }
                    System.out.println(result_list);
                    System.out.println("Fetched all rows from JDBC ResultSet");
                    // Close the ResultSet
                    rs_result.close();
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
