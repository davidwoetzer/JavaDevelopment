/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db2connector_intern;

import java.io.FileNotFoundException;
import java.io.IOException;
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
 * 
 * Remember to add "db2cc.jar" to your library
 */
public class Main {

       static final String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
      // static final String url = "jdbc:db2://contest.lappdomain.lappgroup.com:50000/PEOPLEDB";
       static final String url = "jdbc:db2://IPADDRESS:50000/PEOPLEDB";
       static final String user = "db2admin";
       static final String password = "PASSWORD";

      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

       FetchData fetch = new FetchData();
       fetch._fetchData();
    
    }

}
