package com.refugeofglory.character.factory;

import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.enemy.enemies.*;

public class EnemyFactory {

    public static EnemyPrototype create(String type) {
        return switch (type.toUpperCase()) {
            case "GHOUL"           -> Ghoul.create();
            case "CRAWLING_CLAW"   -> CrawlingClaw.create();
            case "DISPLACER_BEAST" -> DisplacerBeast.create();
            case "GOLEM"           -> Golem.create();
            case "OWLBEAR"         -> Owlbear.create();
            case "DEMON"           -> Demon.create();
            default -> throw new IllegalArgumentException("Inimigo desconhecido: " + type);
        };
    }
}