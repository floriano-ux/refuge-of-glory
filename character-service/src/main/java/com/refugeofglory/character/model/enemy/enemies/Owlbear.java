package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class Owlbear {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                110, 110, 0, 0,
                15, 25, 13, 3, 0, 1
        );
        return new EnemyPrototype(null, "Urso-Coruja", "owlbear_sprite", attrs);
    }
}