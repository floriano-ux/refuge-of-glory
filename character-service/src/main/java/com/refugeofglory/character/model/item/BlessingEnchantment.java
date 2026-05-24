package com.refugeofglory.character.model.item;

import com.refugeofglory.character.model.player.Character;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BLESSING_ENCHANTMENT")
@NoArgsConstructor
public class BlessingEnchantment extends ItemDecorator {

    @Column(nullable = true)
    private int bonusHeal;

    public BlessingEnchantment(Item wrappedItem, int bonusHeal) {
        super(wrappedItem);
        this.bonusHeal = bonusHeal;
    }

    @Override
    public String getName() {
        return wrappedItem.getName() + " [Bênção]";
    }

    @Override
    public void applyEffect(Character target) {
        wrappedItem.applyEffect(target);
        target.getAttributes().restoreHealth(bonusHeal);
    }
}