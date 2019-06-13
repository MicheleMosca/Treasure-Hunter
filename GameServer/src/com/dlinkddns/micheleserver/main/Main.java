package com.dlinkddns.micheleserver.main;

import java.sql.*;

public class Main
{
    public static final String JDBCDriverMySQL = "com.mysql.jdbc.Driver";
    public static final String JDBCURLMySQL = "jdbc:mysql://micheleserver.dlinkddns.com:3306/gamecloud?user=user&password=miokmiok";

    public static void main(String[] args)
    {
        System.out.println("Server game started...");
        try
        {
            Class.forName(JDBCDriverMySQL);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Lib not found!");
        }

        try
        {
            Connection connection = DriverManager.getConnection(JDBCURLMySQL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            System.out.println("Connessione al db effettuata");

            ResultSet rs = statement.executeQuery("SELECT * FROM prova");
            while (rs.next()) {
                System.out.printf("%s \n",
                        rs.getString("nome"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
