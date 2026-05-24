package com.refugeofglory.character.model.item;

import com.refugeofglory.character.model.player.Character;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@MappedSuperclass
public abstract class ItemDecorator extends Item {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wrapped_item_id", nullable = true)
    protected Item wrappedItem;

    public ItemDecorator(Item wrappedItem) {
        super(wrappedItem.getName());
        this.wrappedItem = wrappedItem;
    }

    @Override
    public String getName() {
        return wrappedItem.getName();
    }

    @Override
    public void applyEffect(Character target) {
        wrappedItem.applyEffect(target);
    }
}