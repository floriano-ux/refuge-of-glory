package com.refugeofglory.combat.state;

import com.refugeofglory.combat.battle.BattleSession;

public interface BattleState {
    void handleTurn(BattleSession session);
    String getStateName();
}