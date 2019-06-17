package com.game;

public class User
{
    private String username;
    private int lastLevel;

    public User(String username, int lastLevel)
    {
        this.username = username;
        this.lastLevel = lastLevel;
    }

    public String getUsername()
    {
        return username;
    }

    public int getLastLevel()
    {
        return lastLevel;
    }
}
