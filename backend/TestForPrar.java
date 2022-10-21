package backend;

import java.util.ArrayList;

import java.sql.*;
import java.util.*;


public class TestForPrar {
    public static void main(String[] args) {
        ArrayList<String> testFacilities = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        String PASS = "";

        try {
            Connection conn = (DriverManager.getConnection(DB_URL, USER, PASS));
            conn.setAutoCommit(true);
            try(Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM just_in_case.building");
                new Building("testname", "testdesc", testFacilities);
                ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE just_in_case.building.buildingName = 'testname'"); 
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
