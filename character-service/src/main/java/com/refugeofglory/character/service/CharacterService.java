package com.refugeofglory.character.service;

import com.refugeofglory.character.builder.CharacterBuilder;
import com.refugeofglory.character.factory.BarbarianFactory;
import com.refugeofglory.character.factory.CharacterFactory;
import com.refugeofglory.character.factory.EnemyFactory;
import com.refugeofglory.character.factory.SwordsmanFactory;
import com.refugeofglory.character.model.dto.CharacterDTO;
import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Character;
import com.refugeofglory.character.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.refugeofglory.character.model.player.Inventory;
import com.refugeofglory.character.model.item.HealthPotion;
import com.refugeofglory.character.model.item.FireEnchantment;
import com.refugeofglory.character.model.item.BlessingEnchantment;
import com.refugeofglory.character.model.item.Item;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public Character createHero(String name, String characterClass, Long userId) {
        CharacterFactory factory = switch (characterClass.toUpperCase()) {
            case "SWORDSMAN" -> new SwordsmanFactory();
            case "BARBARIAN" -> new BarbarianFactory();
            default -> throw new IllegalArgumentException("Classe inválida: " + characterClass);
        };

        Character base = factory.createCharacter();
        Character hero = new CharacterBuilder(base)
                .withName(name)
                .withUserId(userId)
                .build();

        return characterRepository.save(hero);
    }

    public List<EnemyPrototype> spawnEnemy(String type, int n) {
        EnemyPrototype prototype = EnemyFactory.create(type);
        List<EnemyPrototype> enemies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            enemies.add(prototype.clone());
        }
        return enemies;
    }

    public CharacterDTO toDTO(Character character) {
        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getCharacterClass(),
                character.getLevel(),
                character.getAttributes().getCurrentHp(),
                character.getAttributes().getMaxHp(),
                character.getAttributes().getMinDamage(),
                character.getAttributes().getMaxDamage(),
                character.getAttributes().getDefense(),
                character.getAttributes().getSpeed(),
                character.getInitiativePoints()
        );
    }

    public Character getCharacter(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public List<Character> getCharactersByUser(Long userId) {
        return characterRepository.findByUserId(userId);
    }
    public Inventory addItemToInventory(Long characterId, String itemType, int healAmount) {
        Character character = getCharacter(characterId);

        HealthPotion potion = new HealthPotion(itemType, healAmount);

        if (!character.getInventory().addItem(potion)) {
            throw new RuntimeException("Inventário cheio!");
        }

        characterRepository.save(character);
        return character.getInventory();
    }
    public Inventory enchantItem(Long characterId, Long itemId, String enchantType, int bonusValue) {
        Character character = getCharacter(characterId);
        Inventory inventory = character.getInventory();

        Item item = inventory.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        inventory.getItems().remove(item);

        Item enchanted = switch (enchantType.toUpperCase()) {
            case "FIRE"     -> new FireEnchantment(item, bonusValue);
            case "BLESSING" -> new BlessingEnchantment(item, bonusValue);
            default -> throw new IllegalArgumentException("Enchantment inválido: " + enchantType);
        };

        inventory.getItems().add(enchanted);
        characterRepository.save(character);
        return inventory;
    }
}