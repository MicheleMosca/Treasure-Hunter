package com.game;

public class User
{
    private String username;
    private int lastLevel;
    private int levelSelected;

    public User(String username, int lastLevel)
    {
        this.username = username;
        this.lastLevel = lastLevel;
        levelSelected = 1;
    }

    public void selectLevel(int levelSelected)
    {
        this.levelSelected = levelSelected;
    }

    public int getLevelSelected()
    {
        return levelSelected;
    }

    public void addNewLevel()
    {
        lastLevel++;
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
