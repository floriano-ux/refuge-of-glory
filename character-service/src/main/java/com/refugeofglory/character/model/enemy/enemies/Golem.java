package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class Golem {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                100, 100, 0, 0,
                18, 28, 20, 4, 0, 1
        );
        return new EnemyPrototype(null, "Golem", "golem_sprite", attrs);
    }
}