package com.game.graphics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.Chest;
import com.game.graphics.entities.Coin;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.enemies.Snake;
import com.game.graphics.entities.enemies.Spike;
import com.game.graphics.entities.players.Carl;
import com.game.screens.PlayScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che server per creare gli oggetti del mondo di gioco in base ad una TiledMap data in input
 * (Non fa altro che cercare all'interno della mappa oggetti con determinati nomi per poi instanziare le loro rispettive classi)
 */

public class WorldCreator
{
    private PlayScreen playScreen;

    public WorldCreator(PlayScreen playScreen)
    {
        this.playScreen = playScreen;
    }

    /**
     * Metodo per inizializzare il mondo di gioco
     * @param tiledMap TiledMap da cui pendere gli oggetti di gioco
     * @param world Mondo su cui inserirli
     * @return Lista di AnimatedEntity contenente tutti gli oggetti di gioco
     */
    public List<AnimatedEntity> initWorld(TiledMap tiledMap, World world)
    {
        List<AnimatedEntity> gameObjects = new ArrayList<AnimatedEntity>();

        // Inserisco nel mondo tutti gli oggetti di tipo ground della mappa con le rispettive collisioni,
        // ma non prelevo il loro riferimento
        for (MapObject mapObject : tiledMap.getLayers().get("Terrain").getObjects())
            new Entity(world, mapObject, BodyDef.BodyType.StaticBody);

        // Inserisco nel mondo i nemici statici
        for (MapObject mapObject : tiledMap.getLayers().get("Enemies").getObjects())
        {
            if (mapObject.getName().equals("spike"))
                new Spike(world, mapObject, BodyDef.BodyType.StaticBody);
            else if (mapObject.getName().equals("snake"))
            	gameObjects.add(new Snake(world, mapObject, BodyDef.BodyType.StaticBody, playScreen));
        }

        // Inserisco nel mondo i coin con i relativi spawn points
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("coinSpawn"))
                gameObjects.add(new Coin(world, mapObject));
        }

        // Inserisco nel mondo la chest di fine livello
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("finishSpawn"))
                gameObjects.add(new Chest(world, mapObject, playScreen));
        }

        // Inserisco nel mondo i trigger per il tutorial
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("spaceSpawn"))
                new TutorialObject(world, mapObject, BodyDef.BodyType.StaticBody, playScreen,
                        "To jump press", "Space", AnimationState.Jump);

            else if (mapObject.getName().equals("ctrlSpawn"))
                new TutorialObject(world, mapObject, BodyDef.BodyType.StaticBody, playScreen,
                        "To slide press", "ctrl", AnimationState.Slide);

            else if (mapObject.getName().equals("endSpawn"))
                new TutorialObject(world, mapObject, BodyDef.BodyType.StaticBody, playScreen,
                        "Take the chest", "", AnimationState.Run);
        }

        // Inserisco nel mondo il player in uso con il relativo spawn point
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("playerSpawn"))
                gameObjects.add(new Carl(world, mapObject, playScreen));
        }

        return gameObjects;
    }
}
