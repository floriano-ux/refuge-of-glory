package com.refugeofglory.character.client;

import com.refugeofglory.character.model.dto.CharacterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CharacterClient {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    private final WebClient.Builder webClientBuilder;

    public CharacterDTO fetchCharacterData(Long id) {
        return webClientBuilder.build()
                .get()
                .uri(authServiceUrl + "/characters/" + id)
                .retrieve()
                .bodyToMono(CharacterDTO.class)
                .block();
    }
}