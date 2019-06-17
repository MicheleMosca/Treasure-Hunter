package com.game.graphics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.Chest;
import com.game.graphics.entities.Coin;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.enemies.Spike;
import com.game.graphics.entities.players.Carl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WorldCreator
{
    public static List<AnimatedEntity> initWorld(TiledMap tiledMap, World world)
    {
        List<AnimatedEntity> gameObjects = new LinkedList<AnimatedEntity>();

        // Inserisco nel mondo tutti gli oggetti di tipo ground della mappa con le rispettive collisioni,
        // ma non prelevo il loro riferimento
        for (MapObject mapObject : tiledMap.getLayers().get("Terrain").getObjects())
            new Entity(world, mapObject, BodyDef.BodyType.StaticBody);

        // Inserisco nel mondo i nemici statici
        for (MapObject mapObject : tiledMap.getLayers().get("Enemies").getObjects())
        {
            if (mapObject.getName().equals("spike"))
                new Spike(world, mapObject, BodyDef.BodyType.StaticBody);
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
                gameObjects.add(new Chest(world, mapObject));
        }

        // Inserisco nel mondo il player in uso con il relativo spawn point
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("playerSpawn"))
                gameObjects.add(new Carl(world, mapObject));

            // Inserisco nel mondo il punto di fine del gioco
            else if (mapObject.getName().equals("finishSpawn"))
                gameObjects.add(new );
        }

        return gameObjects;
    }
}
