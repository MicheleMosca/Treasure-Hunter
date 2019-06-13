package com.gameserver.dbManager;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBManager
{
    public static final String JDBCDriverMySQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String JDBCURLMySQL = "jdbc:sqlserver://localhost;databaseName=gamecloud;user=user;password=miokmiok";
    private Statement statement;

    public DBManager()
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
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            System.out.println("Connessione al db effettuata");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<String> getNames()
    {
        List<String> result = new LinkedList<String>();
        ResultSet rs = null;
        try
        {
            rs = statement.executeQuery("SELECT * FROM Utenti");
            while (rs.next())
            {
                result.add(rs.getString("nome"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
