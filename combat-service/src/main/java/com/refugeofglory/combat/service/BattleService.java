package com.refugeofglory.combat.service;

import com.refugeofglory.combat.battle.BattleSession;
import com.refugeofglory.combat.client.CharacterClient;
import com.refugeofglory.combat.model.dto.BattleParticipantDTO;
import com.refugeofglory.combat.observer.BattleObserver;
import com.refugeofglory.combat.strategy.DamageStrategyFactory;
import com.refugeofglory.combat.strategy.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class BattleService {

    private final CharacterClient characterClient;
    private final DamageStrategyFactory damageStrategyFactory;
    private final List<BattleObserver> observers;

    private final Map<Long, BattleSession> activeBattles = new HashMap<>();
    private final AtomicLong battleIdCounter = new AtomicLong(1);

    public BattleSession startBattle(Long characterId, String enemyType) {
        BattleParticipantDTO player = characterClient.fetchCharacter(characterId);
        player.setPlayer(true);

        BattleParticipantDTO enemy = createEnemy(enemyType);
        enemy.setPlayer(false);

        Long battleId = battleIdCounter.getAndIncrement();
        BattleSession session = new BattleSession(
                battleId, player, enemy, damageStrategyFactory, observers
        );

        activeBattles.put(battleId, session);
        session.notifyObservers("Batalha " + battleId + " iniciada! " +
                player.getName() + " vs " + enemy.getName());

        return session;
    }

    public BattleSession executeAction(Long battleId, String damageType) {
        BattleSession session = getBattle(battleId);

        if (session.isOver()) {
            throw new RuntimeException("Batalha já encerrada!");
        }

        DamageType type = DamageType.valueOf(damageType.toUpperCase());
        session.executeAction(type);

        if (session.isOver()) {
            activeBattles.remove(battleId);
        }

        return session;
    }

    public BattleSession getBattle(Long battleId) {
        BattleSession session = activeBattles.get(battleId);
        if (session == null) {
            throw new RuntimeException("Batalha não encontrada: " + battleId);
        }
        return session;
    }

    private BattleParticipantDTO createEnemy(String enemyType) {
        return switch (enemyType.toUpperCase()) {
            case "GHOUL" -> new BattleParticipantDTO(
                    null, "Carniçal", "ENEMY",
                    18, 18, 4, 7, 0, 6, 6.0,
                    false, false, 0);
            case "CRAWLING_CLAW" -> new BattleParticipantDTO(
                    null, "Garra Rastejante", "ENEMY",
                    8, 8, 2, 4, 0, 8, 8.0,
                    false, false, 0);
            case "DISPLACER_BEAST" -> new BattleParticipantDTO(
                    null, "Pantera Deslocadora", "ENEMY",
                    75, 75, 10, 16, 13, 12, 18.5,
                    false, false, 0);
            case "GOLEM" -> new BattleParticipantDTO(
                    null, "Golem", "ENEMY",
                    100, 100, 18, 28, 20, 4, 14.0,
                    false, false, 0);
            case "OWLBEAR" -> new BattleParticipantDTO(
                    null, "Urso-Coruja", "ENEMY",
                    110, 110, 15, 25, 13, 3, 9.5,
                    false, false, 0);
            case "DEMON" -> new BattleParticipantDTO(
                    null, "Demônio", "ENEMY",
                    150, 150, 28, 48, 10, 7, 12.0,
                    false, false, 0);
            default -> throw new IllegalArgumentException("Inimigo desconhecido: " + enemyType);
        };
    }
}