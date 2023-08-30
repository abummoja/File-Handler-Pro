/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Abummoja
 */
public class DataBaseManager {

    public static String[] hist = {""};
    final static List<String> histList = new ArrayList<>(Arrays.asList(hist));
    private static final String URL = "jdbc:sqlite:recents.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, path TEXT)";
    private static final String INSERT_DATA_SQL = "INSERT INTO my_table (path) VALUES (?, ?)";
    private static final String SELECT_DATA_SQL = "SELECT * FROM my_table";

    public static void add(String pathname){
         // insert data into the table
        try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(INSERT_DATA_SQL)) {
            pstmt.setString(1, pathname);
            //pstmt.setInt(2, 30);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static String[] getHist(){
         // retrieve data from the table
        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_DATA_SQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("path");
                //int age = rs.getInt("age");
                histList.add(name);
                System.out.printf("ID: %d, Name: %s", id, name);
            }
            hist=histList.toArray(new String[histList.size()]);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hist;
    }
    public static void creator(){
         // retrieve data from the table
        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_DATA_SQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("path");
                //int age = rs.getInt("age");

                System.out.printf("ID: %d, Name: %s", id, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        
    }
}
