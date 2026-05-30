package com.refugeofglory.combat.client;

import com.refugeofglory.combat.model.dto.BattleParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CharacterClient {

    @Value("${character.service.url}")
    private String characterServiceUrl;

    private final WebClient.Builder webClientBuilder;

    public BattleParticipantDTO fetchCharacter(Long characterId) {
        return webClientBuilder.build()
                .get()
                .uri(characterServiceUrl + "/characters/" + characterId)
                .retrieve()
                .bodyToMono(BattleParticipantDTO.class)
                .block();
    }
}