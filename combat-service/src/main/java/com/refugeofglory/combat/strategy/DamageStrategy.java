package com.refugeofglory.combat.strategy;

public interface DamageStrategy {
    int calculate(int minDamage, int maxDamage, int attackerDefense, int defenderDefense);
}