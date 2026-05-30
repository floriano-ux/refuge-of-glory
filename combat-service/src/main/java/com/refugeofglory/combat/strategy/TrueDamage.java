package com.refugeofglory.combat.strategy;

import org.springframework.stereotype.Component;

@Component
public class TrueDamage implements DamageStrategy {

    @Override
    public int calculate(int minDamage, int maxDamage, int attackerDefense, int defenderDefense) {
        return minDamage + (int)(Math.random() * (maxDamage - minDamage + 1));
    }
}