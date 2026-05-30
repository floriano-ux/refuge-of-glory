package com.refugeofglory.combat.state;

import com.refugeofglory.combat.battle.BattleSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerTurnState implements BattleState {

    @Override
    public void handleTurn(BattleSession session) {
        log.info("Turno do jogador — aguardando ação...");
        session.notifyObservers("Turno do jogador iniciado");
    }

    @Override
    public String getStateName() {
        return "PLAYER_TURN";
    }
}