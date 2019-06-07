package com.game.graphics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.enemies.Spike;
import com.game.graphics.entities.players.Carl;

import java.util.HashMap;

public class WorldCreator
{
    public static HashMap<String, AnimatedEntity> initWorld(TiledMap tiledMap, World world)
    {
        HashMap<String, AnimatedEntity> gameObjects = new HashMap<String, AnimatedEntity>();

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

        // Inserisco nel mondo il player in uso con il relativo spawn point
        for (MapObject mapObject : tiledMap.getLayers().get("Spawn").getObjects())
        {
            if (mapObject.getName().equals("playerSpawn"))
                gameObjects.put("player", new Carl(world, mapObject));
        }

        return gameObjects;
    }
}
