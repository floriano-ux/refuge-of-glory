package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class DisplacerBeast {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                75, 75, 0, 0,
                10, 16, 13, 12, 0, 1
        );
        return new EnemyPrototype(null, "Pantera Deslocadora", "displacer_beast_sprite", attrs);
    }
}