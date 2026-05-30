package com.refugeofglory.combat.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DamageStrategyFactory {

    private final PhysicalDamage physicalDamage;
    private final MagicDamage magicDamage;
    private final TrueDamage trueDamage;

    public DamageStrategy getStrategy(DamageType type) {
        return switch (type) {
            case PHYSICAL -> physicalDamage;
            case MAGIC    -> magicDamage;
            case TRUE     -> trueDamage;
        };
    }
}