package com.refugeofglory.combat.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UINotifierObserver implements BattleObserver {

    @Override
    public void onBattleEvent(String event) {
        log.info("Notificando interface: {}", event);
    }
}