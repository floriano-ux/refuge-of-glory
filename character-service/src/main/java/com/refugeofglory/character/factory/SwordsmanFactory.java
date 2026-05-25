package com.refugeofglory.character.factory;

import com.refugeofglory.character.model.player.Attributes;
import com.refugeofglory.character.model.player.Character;
import com.refugeofglory.character.model.player.Inventory;
import com.refugeofglory.character.model.item.Weapon;

public class SwordsmanFactory implements CharacterFactory {

    @Override
    public Character createCharacter() {
        Attributes attrs = new Attributes(
                40, 40, 0, 0,
                5, 17, 5, 5, 2, 10
        );
        Inventory inventory = new Inventory(4);
        inventory.getItems().add(new Weapon("Espada Longa"));

        Character character = new Character();
        character.setCharacterClass("SWORDSMAN");
        character.setLevel(1);
        character.setExperience(0);
        character.setAttributes(attrs);
        character.setInventory(inventory);
        return character;
    }
}