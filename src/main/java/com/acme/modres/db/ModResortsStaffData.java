package com.acme.modres.db;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

@Singleton
@Startup
public class ModResortsStaffData {

    @Resource(lookup = "jdbc/ModResortsJndi")
    private DataSource dataSource;

    private static final String SELECT_STAFF_QUERY = "SELECT * FROM MODRESORTSSTAFF";

    public ArrayList<String> getStaffData() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> staffData = new ArrayList<>();

        try {
            // Get a connection from the injected data source
            conn = dataSource.getConnection();
            // Create a prepared statement
            stmt = conn.prepareStatement(SELECT_STAFF_QUERY);
            // Execute the query
            rs = stmt.executeQuery();

            // Process the results
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();    

            while (rs.next()) {
                String row = "{";
                for (int i = 1; i <= columnCount; i++) {
                    if (i==1) {
                        row += "\"" + metadata.getColumnName(i) + "\":\"" + rs.getString(i) + "\"";   
                    } else {
                        row += ", \"" + metadata.getColumnName(i) + "\":\"" + rs.getString(i) + "\"";   
                    }      
                }
                row += "}";
                staffData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return staffData;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}