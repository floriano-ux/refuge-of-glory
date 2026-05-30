package com.refugeofglory.combat.state;

import com.refugeofglory.combat.battle.BattleSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnemyTurnState implements BattleState {

    @Override
    public void handleTurn(BattleSession session) {
        log.info("Turno do inimigo — calculando ação...");
        session.notifyObservers("Turno do inimigo iniciado");
        session.executeEnemyAction();
    }

    @Override
    public String getStateName() {
        return "ENEMY_TURN";
    }
}