package ca.poc.djj.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://test-rg-d365-qa1dev.eastus.cloudapp.azure.com:51726;"
                        + "database=AxDB;"
                        + "user=builtin\\Admin97941ce7e5;"
                        + "password=34!4yp=f**S!!F;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            String selectSql = "SELECT TOP 10 * from dbo.BRKSHIPMENTINQUIRYVIEW";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
       
        
        	
    }
}