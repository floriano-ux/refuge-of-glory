package com.refugeofglory.character.model.enemy;

import com.refugeofglory.character.model.player.Attributes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enemy_prototypes")
public class EnemyPrototype implements Cloneable<EnemyPrototype> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String spriteId;

    @Embedded
    private Attributes baseStats;

    @Override
    public EnemyPrototype clone() {
        EnemyPrototype clone = new EnemyPrototype();
        clone.setName(this.name);
        clone.setSpriteId(this.spriteId);
        Attributes clonedStats = new Attributes(
                baseStats.getMaxHp(), baseStats.getMaxHp(),
                baseStats.getMaxMp(), baseStats.getMaxMp(),
                baseStats.getMinDamage(), baseStats.getMaxDamage(),
                baseStats.getDefense(), baseStats.getSpeed(),
                baseStats.getDodgeCharges(), baseStats.getMaxLevel()
        );
        clone.setBaseStats(clonedStats);
        return clone;
    }
}