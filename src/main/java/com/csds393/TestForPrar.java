package com.csds393;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
import java.io.*;
import com.csds393.*;

public class TestForPrar {

    public static void run() {
        ArrayList<String> testFacilities = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        
        String DB_URL = "jdbc:mysql://just-in-case.cn0mcjwf4mxn.us-east-1.rds.amazonaws.com:3306";
        String USER = "admin";
        try (Scanner fr = new Scanner(new File("./././untracked.txt"))) {
            String PASS = fr.nextLine();

            try {
                Connection conn = (DriverManager.getConnection(DB_URL, USER, PASS));
                conn.setAutoCommit(true);
                try(Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("DELETE FROM just_in_case.building");
                    BuildingHub bh = new BuildingHub();
                    bh.addBuilding(new Building("testname", "testdesc", testFacilities));

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

                    bh.removeBuildingByName("testname");


                    System.out.print("\nAfter delete: ");
                    rs = stmt.executeQuery(
                    "SELECT * FROM just_in_case.building WHERE just_in_case.building.buildingName = 'testname'"); 
                    rsmd = rs.getMetaData();
                    columnsNumber = rsmd.getColumnCount();
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
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}