package com.refugeofglory.combat.state;

import com.refugeofglory.combat.battle.BattleSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VictoryState implements BattleState {

    @Override
    public void handleTurn(BattleSession session) {
        log.info("Vitória! Concedendo experiência ao jogador...");
        session.notifyObservers("Jogador venceu a batalha!");
        session.grantExperience();
    }

    @Override
    public String getStateName() {
        return "VICTORY";
    }
}