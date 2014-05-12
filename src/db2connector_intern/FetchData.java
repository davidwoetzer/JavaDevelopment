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
 * @author David Woetzer
 */
public class FetchData {

    public static List<String> result_list;

    InsertData _insert = new InsertData();

    public void _fetchData() {

        ResultSet rs_result;
        ResultSet rs_insert;

        Statement stmt;
        String taetigkeit;
        String prof_key;

        Connection connection = null;
        try {

            Class.forName(jdbcClassName);

            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (connection != null) {

                try {
                    stmt = connection.createStatement();

                    rs_result = stmt.executeQuery("SELECT PROF_KEY, PROF_VALUE FROM EMPINST.PROFILE_EXTENSIONS WHERE PROF_PROPERTY_ID='taetigkeit'");

                    result_list = new ArrayList<>();

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

                    rs_result.close();

                    stmt.close();

                    connection.commit();

                    connection.close();

                } catch (SQLException e) {
                }

            }
        }

    }

}
