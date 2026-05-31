package com.refugeofglory.character.model.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String characterClass;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private long experience;

    @Column(nullable = false)
    private Long userId;

    @Embedded
    private Attributes attributes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public void gainExperience(long amount) {
        this.experience += amount;
    }

    public void levelUp() {
        if (this.level < this.attributes.getMaxLevel()) {
            this.level++;
            applyLevelUpStats();
        }
    }

    private void applyLevelUpStats() {
        Attributes attrs = this.attributes;
        if (this.characterClass.equals("SWORDSMAN")) {
            attrs.setMaxHp(attrs.getMaxHp() + 5);
            attrs.setCurrentHp(attrs.getMaxHp());
            attrs.setMinDamage(attrs.getMinDamage() + 1);
            attrs.setMaxDamage(attrs.getMaxDamage() + 1);
        } else if (this.characterClass.equals("BARBARIAN")) {
            attrs.setMaxHp(attrs.getMaxHp() + 7);
            attrs.setCurrentHp(attrs.getMaxHp());
            attrs.setMinDamage(attrs.getMinDamage() + 2);
            attrs.setMaxDamage(attrs.getMaxDamage() + 2);
            if (this.level % 3 == 0) {
                attrs.setDefense(attrs.getDefense() + 1);
            }
        }
    }

    public boolean isAlive() {
        return this.attributes.isAlive();
    }

    public double getInitiativePoints() {
        return this.attributes.getInitiativePoints();
    }
}