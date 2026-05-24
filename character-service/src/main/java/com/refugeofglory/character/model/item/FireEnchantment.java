package com.refugeofglory.character.model.item;

import com.refugeofglory.character.model.player.Character;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FIRE_ENCHANTMENT")
@NoArgsConstructor
public class FireEnchantment extends ItemDecorator {

    @Column(nullable = true)
    private int bonusDamage;

    public FireEnchantment(Item wrappedItem, int bonusDamage) {
        super(wrappedItem);
        this.bonusDamage = bonusDamage;
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " [Fogo]";
    }

    @Override
    public void applyEffect(Character target) {
        wrappedItem.applyEffect(target);
        target.getAttributes().receiveDamage(bonusDamage);
    }
}