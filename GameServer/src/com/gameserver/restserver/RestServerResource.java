package com.gameserver.restserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.gameserver.dbManager.DBManager;
import org.json.JSONArray;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Classe per la gestione del server REST basato sulla connessione ad un database
 */

public class RestServerResource extends ServerResource
{
    public static DBManager dbManager;

    /**
     * Metodo universale per ottenere un json array di json object presi dal risultato di una query
     * @param resultSet result set della query procesata
     * @return JSON array in forma di stringa
     * @throws SQLException Eventuali errori sql devono essere gestiti dal chiamante
     */
    private String resultset_to_json(ResultSet resultSet) throws SQLException
    {
        JSONArray jarr = new JSONArray();
        while (resultSet.next())
        {
            HashMap<String, String> row = new HashMap<String, String>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                row.put(resultSet.getMetaData().getColumnName(i), String.valueOf(resultSet.getObject(i)));
            jarr.put(row);
        }
        return jarr.toString();
    }

    /**
     * Metodo per la gestione delle connessioni in arrivo
     * @return Risposta all'utente
     */
    @Get
    public String handleConnection()
    {
        String response = null;
        try
        {
            if (getReference().getLastSegment().equals("getClassifica"))
            {
                response = resultset_to_json(dbManager.getClassifica(getQuery().getValues("livello")));
            }
            else if (getReference().getLastSegment().equals("checkUser"))
            {
                response = String.valueOf(dbManager.checkUser(getQuery().getValues("username"), getQuery().getValues("password")));
            }
            else if (getReference().getLastSegment().equals("updateRecord"))
            {
                response = String.valueOf(dbManager.updateRecord(getQuery().getValues("username"),
                        getQuery().getValues("livello"), Integer.parseInt(getQuery().getValues("coins")),
                        getQuery().getValues("time")));
            }
            else
            {
                throw new ResourceException(new Status(Status.SERVER_ERROR_NOT_IMPLEMENTED, ""));
            }
        } catch (Exception e)
        {
            System.out.println("[ERROR]" + getReference());
        }
        return response;
    }

    public static void main(String[] args) throws Exception
    {
        dbManager = new DBManager();
        new Server(Protocol.HTTP, 4444, RestServerResource.class).start();
    }
}