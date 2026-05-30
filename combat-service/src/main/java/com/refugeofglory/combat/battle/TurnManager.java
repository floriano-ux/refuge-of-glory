package com.refugeofglory.combat.battle;

import com.refugeofglory.combat.model.dto.BattleParticipantDTO;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class TurnManager {

    private int currentTurn;
    private List<BattleParticipantDTO> turnOrder;

    public TurnManager(List<BattleParticipantDTO> participants) {
        this.currentTurn = 0;
        this.turnOrder = participants.stream()
                .sorted(Comparator.comparingDouble(BattleParticipantDTO::getInitiativePoints).reversed())
                .toList();
    }

    public BattleParticipantDTO getCurrentActor() {
        return turnOrder.get(currentTurn % turnOrder.size());
    }

    public void nextTurn() {
        this.currentTurn++;
    }

    public boolean isPlayerTurn() {
        return getCurrentActor().isPlayer();
    }

    public int getTurnNumber() {
        return currentTurn + 1;
    }
}