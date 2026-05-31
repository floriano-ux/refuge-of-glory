package com.refugeofglory.character.model.player;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Attributes {

    private int maxHp;
    private int currentHp;
    private int maxMp;
    private int currentMp;
    private int minDamage;
    private int maxDamage;
    private int defense;
    private int speed;
    private int dodgeCharges;
    private int maxLevel;

    public double getInitiativePoints() {
        return speed + (defense * 0.5);
    }

    public void receiveDamage(int amount) {
        this.currentHp = Math.max(0, this.currentHp - amount);
    }

    public void restoreHealth(int amount) {
        this.currentHp = Math.min(this.maxHp, this.currentHp + amount);
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public int rollDamage() {
        return minDamage + (int)(Math.random() * (maxDamage - minDamage + 1));
    }
}