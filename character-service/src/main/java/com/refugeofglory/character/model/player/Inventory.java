package com.refugeofglory.character.model.player;

import com.refugeofglory.character.model.item.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private int maxSlots;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "inventory_id")
private List<Item> items = new ArrayList<>();

public Inventory(int maxSlots) {
    this.maxSlots = maxSlots;
}

public boolean addItem(Item item) {
    if (items.size() < maxSlots) {
        items.add(item);
        return true;
    }
    return false;
}

public boolean removeItem(Long itemId) {
    return items.removeIf(i -> i.getId().equals(itemId));
}

public void useAllItems(Character target) {
    items.forEach(item -> item.applyEffect(target));
    items.clear();
}

public boolean isFull() {
    return items.size() >= maxSlots;
}
}