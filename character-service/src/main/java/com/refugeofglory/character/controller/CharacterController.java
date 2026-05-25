package com.refugeofglory.character.controller;

import com.refugeofglory.character.model.dto.CharacterDTO;
import com.refugeofglory.character.model.player.Character;
import com.refugeofglory.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.refugeofglory.character.model.enemy.EnemyPrototype;
import com.refugeofglory.character.model.player.Inventory;
import com.refugeofglory.character.model.item.HealthPotion;
import com.refugeofglory.character.model.item.FireEnchantment;
import com.refugeofglory.character.model.item.BlessingEnchantment;
import com.refugeofglory.character.model.item.Item;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<Character> createHero(
            @RequestParam String name,
            @RequestParam String characterClass,
            @RequestParam Long userId) {
        return ResponseEntity.ok(characterService.createHero(name, characterClass, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.toDTO(characterService.getCharacter(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Character>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(characterService.getCharactersByUser(userId));
    }

    @GetMapping("/spawn")
    public ResponseEntity<List<EnemyPrototype>> spawnEnemy(
            @RequestParam String type,
            @RequestParam int n) {
        return ResponseEntity.ok(characterService.spawnEnemy(type, n));
    }
    @PostMapping("/{characterId}/items")
    public ResponseEntity<Inventory> addItem(
            @PathVariable Long characterId,
            @RequestParam String itemType,
            @RequestParam int healAmount) {
        return ResponseEntity.ok(characterService.addItemToInventory(characterId, itemType, healAmount));
    }
    @PostMapping("/{characterId}/items/{itemId}/enchant")
    public ResponseEntity<Inventory> enchantItem(
            @PathVariable Long characterId,
            @PathVariable Long itemId,
            @RequestParam String enchantType,
            @RequestParam int bonusValue) {
        return ResponseEntity.ok(characterService.enchantItem(characterId, itemId, enchantType, bonusValue));
    }
}