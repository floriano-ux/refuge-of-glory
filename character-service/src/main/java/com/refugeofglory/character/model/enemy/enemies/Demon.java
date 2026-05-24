package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class Demon {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                150, 150, 0, 0,
                28, 48, 10, 7, 0, 1
        );
        return new EnemyPrototype(null, "Demônio", "demon_sprite", attrs);
    }
}