package com.refugeofglory.character.model.item;

import com.refugeofglory.character.model.player.Character;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("HEALTH_POTION")
@NoArgsConstructor
public class HealthPotion extends Item {

    @Column(nullable = true)
    private int healAmount;

    public HealthPotion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    @Override
    public void applyEffect(Character target) {
        target.getAttributes().restoreHealth(healAmount);
    }
}