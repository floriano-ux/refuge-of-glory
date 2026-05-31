package com.refugeofglory.character.repository;

import com.refugeofglory.character.model.player.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUserId(Long userId);
}