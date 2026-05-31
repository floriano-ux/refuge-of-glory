package com.refugeofglory.character.factory;

import com.refugeofglory.character.model.player.Attributes;
import com.refugeofglory.character.model.player.Character;
import com.refugeofglory.character.model.player.Inventory;
import com.refugeofglory.character.model.item.Weapon;

public class BarbarianFactory implements CharacterFactory {

    @Override
    public Character createCharacter() {
        Attributes attrs = new Attributes(
                50, 50, 0, 0,
                8, 20, 5, 2, 0, 10
        );
        Inventory inventory = new Inventory(3);
        inventory.getItems().add(new Weapon("Machado de Guerra"));

        Character character = new Character();
        character.setCharacterClass("BARBARIAN");
        character.setLevel(1);
        character.setExperience(0);
        character.setAttributes(attrs);
        character.setInventory(inventory);
        return character;
    }
}