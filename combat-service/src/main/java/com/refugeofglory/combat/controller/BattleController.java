package com.refugeofglory.combat.controller;

import com.refugeofglory.combat.battle.BattleSession;
import com.refugeofglory.combat.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battles")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @PostMapping("/start")
    public ResponseEntity<BattleSession> startBattle(
            @RequestParam Long characterId,
            @RequestParam String enemyType) {
        return ResponseEntity.ok(battleService.startBattle(characterId, enemyType));
    }

    @PostMapping("/{battleId}/action")
    public ResponseEntity<BattleSession> executeAction(
            @PathVariable Long battleId,
            @RequestParam String damageType) {
        return ResponseEntity.ok(battleService.executeAction(battleId, damageType));
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<BattleSession> getBattle(@PathVariable Long battleId) {
        return ResponseEntity.ok(battleService.getBattle(battleId));
    }
}