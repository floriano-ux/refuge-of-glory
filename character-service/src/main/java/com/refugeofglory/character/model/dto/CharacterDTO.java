package com.refugeofglory.character.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {
    private Long id;
    private String name;
    private String characterClass;
    private int level;
    private int currentHp;
    private int maxHp;
    private int minDamage;
    private int maxDamage;
    private int defense;
    private int speed;
    private double initiativePoints;
    private boolean hasFireEnchantment;
    private int fireEnchantmentBonus;
}