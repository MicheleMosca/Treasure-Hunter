package com.game;

/**
 * Classe che definisce un utente all'interno del server
 */
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

    /**
     * Metodo per inserire quale livello da giocare ha scelto l'utente
     * @param levelSelected Numero del livello selezionato
     */
    public void selectLevel(int levelSelected)
    {
        this.levelSelected = levelSelected;
    }

    /**
     * Metodo per ottenere quale livello da giocare ha selezionato l'utente
     * @return Il numero corrispondente al livello selezionato
     */
    public int getLevelSelected()
    {
        return levelSelected;
    }

    /**
     * Metodo per aggiungere il livello appena giocato al numero di livelli che l'utente ha giocato
     * @param levelSelected Numero corrispondente al livello appena giocato
     */
    public void addNewLevel(int levelSelected)
    {
        if (lastLevel < levelSelected)
            lastLevel++;
    }

    /**
     * Metodo per ottenere l'username dell'utente
     * @return Stringa contenente l'username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Ultimo livello che l'utente ha completato
     * @return Numero corrispondente al livello
     */
    public int getLastLevel()
    {
        return lastLevel;
    }
}
