package com.gameserver.restserver;

import java.util.Calendar;

import com.gameserver.dbManager.DBManager;
import org.json.JSONObject;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class RestServerResource extends ServerResource
{
    public static DBManager dbManager;

    public String getDate()
    {
        JSONObject obj = new JSONObject();
        obj.put("machine", "pippo.frida.org");
        obj.put("year", Calendar.getInstance().get(Calendar.YEAR));
        obj.put("month", Calendar.getInstance().get(Calendar.MONTH));
        obj.put("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        return obj.toString();
    }

    public String getTime()
    {
        JSONObject obj = new JSONObject();
        obj.put("machine", "pippo.frida.org");
        obj.put("second", Calendar.getInstance().get(Calendar.SECOND));
        obj.put("minute", Calendar.getInstance().get(Calendar.MINUTE));
        obj.put("hour", Calendar.getInstance().get(Calendar.HOUR));
        return obj.toString();
    }

    public String getNames()
    {
        JSONObject obj = new JSONObject();
        for (String str : dbManager.getNames())
        {
            obj.put("nome", dbManager.getNames());
        }
        return obj.toString();
    }

    @Get
    public String handleConnection()
    {
        String response = null;
        try
        {
            if (getReference().getLastSegment().equals("current_date"))
            {
                response = getDate();
            } else if (getReference().getLastSegment().equals("current_time"))
            {
                response = getTime();
            } else if (getReference().getLastSegment().equals("names"))
            {
                response = getNames();
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