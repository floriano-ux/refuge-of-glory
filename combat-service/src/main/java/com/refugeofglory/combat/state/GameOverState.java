package com.refugeofglory.combat.state;

import com.refugeofglory.combat.battle.BattleSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameOverState implements BattleState {

    @Override
    public void handleTurn(BattleSession session) {
        log.info("Game Over! O jogador foi derrotado...");
        session.notifyObservers("Jogador foi derrotado!");
    }

    @Override
    public String getStateName() {
        return "GAME_OVER";
    }
}