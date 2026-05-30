package com.refugeofglory.combat.strategy;

import org.springframework.stereotype.Component;

@Component
public class PhysicalDamage implements DamageStrategy {

    @Override
    public int calculate(int minDamage, int maxDamage, int attackerDefense, int defenderDefense) {
        int baseDamage = minDamage + (int)(Math.random() * (maxDamage - minDamage + 1));
        int finalDamage = Math.max(0, baseDamage - defenderDefense);
        return finalDamage;
    }
}