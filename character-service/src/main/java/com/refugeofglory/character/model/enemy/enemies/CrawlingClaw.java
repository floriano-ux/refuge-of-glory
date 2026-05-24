package com.refugeofglory.character.model.enemy.enemies;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Attributes;

public class CrawlingClaw {
    public static EnemyPrototype create() {
        Attributes attrs = new Attributes(
                8, 8, 0, 0,
                2, 4, 0, 8, 0, 1
        );
        return new EnemyPrototype(null, "Garra Rastejante", "crawling_claw_sprite", attrs);
    }
}