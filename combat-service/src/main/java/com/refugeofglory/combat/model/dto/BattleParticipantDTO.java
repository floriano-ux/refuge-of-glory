package com.refugeofglory.combat.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleParticipantDTO {
    private Long id;
    private String name;
    private String characterClass;
    private int currentHp;
    private int maxHp;
    private int minDamage;
    private int maxDamage;
    private int defense;
    private int speed;
    private double initiativePoints;
    private Boolean player;
    private Boolean hasFireEnchantment;
    private int fireEnchantmentBonus;

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void receiveDamage(int amount) {
        this.currentHp = Math.max(0, this.currentHp - amount);
    }
}