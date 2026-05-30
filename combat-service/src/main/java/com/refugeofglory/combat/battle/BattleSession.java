package com.refugeofglory.combat.battle;

import com.refugeofglory.combat.model.dto.BattleParticipantDTO;
import com.refugeofglory.combat.observer.BattleObserver;
import com.refugeofglory.combat.state.BattleState;
import com.refugeofglory.combat.state.EnemyTurnState;
import com.refugeofglory.combat.state.GameOverState;
import com.refugeofglory.combat.state.PlayerTurnState;
import com.refugeofglory.combat.state.VictoryState;
import com.refugeofglory.combat.strategy.DamageStrategy;
import com.refugeofglory.combat.strategy.DamageStrategyFactory;
import com.refugeofglory.combat.strategy.DamageType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class BattleSession {

    private Long battleId;
    private BattleState currentState;
    private TurnManager turnManager;
    private List<BattleObserver> observers = new ArrayList<>();
    private DamageStrategyFactory damageStrategyFactory;

    private BattleParticipantDTO player;
    private BattleParticipantDTO enemy;

    public BattleSession(Long battleId,
                         BattleParticipantDTO player,
                         BattleParticipantDTO enemy,
                         DamageStrategyFactory damageStrategyFactory,
                         List<BattleObserver> observers) {
        this.battleId = battleId;
        this.player = player;
        this.enemy = enemy;
        this.damageStrategyFactory = damageStrategyFactory;
        this.observers = observers;
        this.turnManager = new TurnManager(List.of(player, enemy));
        this.currentState = new PlayerTurnState();
    }

    public void attach(BattleObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        observers.forEach(o -> o.onBattleEvent(event));
    }

    public void executeAction(DamageType damageType) {
        currentState.handleTurn(this);

        if (!turnManager.isPlayerTurn()) return;

        DamageStrategy strategy = damageStrategyFactory.getStrategy(damageType);
        int damage = strategy.calculate(
                player.getMinDamage(),
                player.getMaxDamage(),
                player.getDefense(),
                enemy.getDefense()
        );

        if (player.isHasFireEnchantment()) {
            DamageStrategy magicStrategy = damageStrategyFactory.getStrategy(DamageType.MAGIC);
            int fireDamage = magicStrategy.calculate(
                    player.getFireEnchantmentBonus(),
                    player.getFireEnchantmentBonus(),
                    0,
                    enemy.getDefense()
            );
            damage += fireDamage;
            notifyObservers("Dano de fogo adicional: " + fireDamage);
        }

        enemy.receiveDamage(damage);
        notifyObservers("Jogador causou " + damage + " de dano ao " + enemy.getName());

        checkBattleEnd();

        if (enemy.isAlive()) {
            turnManager.nextTurn();
            currentState = new EnemyTurnState();
        }
    }

    public void executeEnemyAction() {
        DamageStrategy strategy = damageStrategyFactory.getStrategy(DamageType.PHYSICAL);
        int damage = strategy.calculate(
                enemy.getMinDamage(),
                enemy.getMaxDamage(),
                enemy.getDefense(),
                player.getDefense()
        );

        player.receiveDamage(damage);
        notifyObservers(enemy.getName() + " causou " + damage + " de dano ao jogador");

        checkBattleEnd();

        if (player.isAlive()) {
            turnManager.nextTurn();
            currentState = new PlayerTurnState();
        }
    }

    public void grantExperience() {
        notifyObservers("Experiência concedida ao jogador!");
    }

    private void checkBattleEnd() {
        if (!enemy.isAlive()) {
            currentState = new VictoryState();
            currentState.handleTurn(this);
        } else if (!player.isAlive()) {
            currentState = new GameOverState();
            currentState.handleTurn(this);
        }
    }

    public boolean isOver() {
        return currentState instanceof VictoryState || currentState instanceof GameOverState;
    }
}