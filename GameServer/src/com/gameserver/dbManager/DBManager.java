package com.gameserver.dbManager;

import java.sql.*;
import java.util.StringTokenizer;

/**
 * Classe che gestisce la connessione con il database
 */

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

    /**
     * Metodo per avere la classifica del gioco per livello
     * @Param livello indicatore del livello di riferimento
     * @return
     */
    public ResultSet getClassifica(String livello)
    {
        ResultSet resultSet = null;
        try
        {
            resultSet = statement.executeQuery("select username, coins, time from Partite where livello = " + livello + " Order by coins desc, time asc");
        } catch (SQLException e)
        {
            System.out.println("Errore: Query Classifica non risolta");
        }
        return resultSet;
    }

    /**
     * Metodo che server sia per controllare se username e password sono corretti sia se un username è disponibile o meno
     * @param username Parametro di username (obbligatorio)
     * @param password Parametro di password (se null controlla se l'username è disponibile)
     * @return per l'opzione 1 (true = valori corretti), per l'opzione 2 (true = username disponibile)
     */
    public boolean checkUser(String username, String password)
    {
        if (password != null)
        {
            try
            {
                ResultSet resultSet = statement.executeQuery("select * from Utenti where username = '" + username +
                        "' and password = '" + password + "'");
                resultSet.next();
                resultSet.getString("username");
                return true;
            } catch (SQLException e)
            {
                return false;
            }
        }

        try
        {
            ResultSet resultSet = statement.executeQuery("select * from Utenti where username = '" + username +
                    "'");
            resultSet.next();
            resultSet.getString("username");
            return false;
        } catch (SQLException e)
        {
            return true;
        }
    }

    /**
     * Metodo che server per aggiornare/inserire un nuovo record all'interno della tabella Partite
     * @param username Username dell'utente ha fatto il record
     * @param livello Livello a cui si riferisce il record
     * @param coins Numero di coind raccolti
     * @param time Tempo impiegato per terminare il livello
     * @return Ritorna vero se l'inserimento è andato a buon fine
     */
    public boolean updateRecord(String username, String livello, int coins, String time)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(time, ":");
        Time newTime = new Time(0, Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
        boolean aggiornato = true;
        try
        {
            ResultSet resultSet = statement.executeQuery("select coins, time from Partite where username = '" + username + "' and livello = " + livello + "");
            resultSet.next();

            System.out.println(coins + " vs " + resultSet.getInt("coins"));

            if (coins == resultSet.getInt("coins"))
            {
                if (newTime.compareTo(resultSet.getTime("time")) < 0)
                    statement.executeUpdate("update Partite set time = '0:" + time + "' where username = '" + username + "' and livello = " + livello);
            }
            else if (coins > resultSet.getInt("coins"))
            {
                statement.executeUpdate("update Partite set coins = " + coins + ", time = '0:" + time + "' where username = '" + username + "' and livello = " + livello);
            }
        } catch (SQLException e)
        {
            aggiornato = false;
        }

        if (!aggiornato)
            try
            {
                statement.executeUpdate("insert into Partite values ('" + username + "', " + livello +
                        ", " + coins + ", '0:" + time + "')");
            } catch (SQLException e)
            {
                System.out.println("Errore: inserimento di un nuovo record in Partite");
                return false;
            }

        return true;
    }

    public ResultSet getLivelli(String username)
    {
        ResultSet resultSet = null;
        try
        {
            resultSet = statement.executeQuery("select livello from Partite where username = '" + username + "' order by livello");
        } catch (SQLException e)
        {
            System.out.println("Errore: Query get Livelli non risolta");
        }
        return resultSet;
    }
}