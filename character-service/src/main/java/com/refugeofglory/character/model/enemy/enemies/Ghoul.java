package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class Ghoul {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                18, 18, 0, 0,
                4, 7, 0, 6, 0, 1
        );
        return new EnemyPrototype(null, "Carniçal", "ghoul_sprite", attrs);
    }
}