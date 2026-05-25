package com.refugeofglory.character.builder;

import com.refugeofglory.character.model.player.Attributes;
import com.refugeofglory.character.model.player.Character;

public class CharacterBuilder {

    private final Character character;

    public CharacterBuilder(Character base) {
        this.character = base;
    }

    public CharacterBuilder withName(String name) {
        this.character.setName(name);
        return this;
    }

    public CharacterBuilder withBaseStats(int str, int agi, int intell) {
        Attributes attrs = this.character.getAttributes();
        attrs.setMinDamage(str);
        attrs.setSpeed(agi);
        attrs.setMaxMp(intell);
        return this;
    }

    public CharacterBuilder withAttributes(Attributes attrs) {
        this.character.setAttributes(attrs);
        return this;
    }

    public CharacterBuilder withUserId(Long userId) {
        this.character.setUserId(userId);
        return this;
    }

    public Character build() {
        if (this.character.getName() == null || this.character.getName().isBlank()) {
            throw new IllegalStateException("Personagem precisa de um nome");
        }
        return this.character;
    }
}