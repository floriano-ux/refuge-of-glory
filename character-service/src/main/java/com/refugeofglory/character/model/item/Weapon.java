package com.refugeofglory.character.model.item;

import com.refugeofglory.character.model.player.Character;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("WEAPON")
@NoArgsConstructor
public class Weapon extends Item {

    public Weapon(String name) {
        super(name);
    }

    @Override
    public void applyEffect(Character target) {
        // Arma não aplica efeito sozinha
        // Só tem efeito quando decorada com FireEnchantment ou BlessingEnchantment
    }
}