package com.mycompany.thebookofmonsters;

import java.util.*;

public class MonsterStorage {
    private final Map<String, List<Monster>> formatStorage;

    public MonsterStorage() {
        this.formatStorage = new HashMap<>();
    }

    public void addFormat(String formatName, List<Monster> newMonsters) {
        // Получаем текущий список или создаем новый, если его нет
        List<Monster> currentMonsters = formatStorage.computeIfAbsent(formatName, k -> new ArrayList<>());
        
        // Добавляем только новых уникальных монстров
        for (Monster newMonster : newMonsters) {
            if (currentMonsters.stream().noneMatch(m -> m.getId() == newMonster.getId())) {
                currentMonsters.add(newMonster);
            }
        }
    }

    public List<Monster> getMonstersByFormat(String formatName) {
        return formatStorage.getOrDefault(formatName, Collections.emptyList());
    }
    
    public Map<String, List<Monster>> getAllFormats() {
        return formatStorage;
    }
    

    public void addNewFormat(String formatName, List<Monster> monsters) {
        formatStorage.put(formatName, new ArrayList<>(monsters));
    }

    private boolean containsMonster(List<Monster> monsters, Monster monster) {
        return monsters.stream().anyMatch(m -> m.getId() == monster.getId());
    }
}