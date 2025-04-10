package com.mycompany.thebookofmonsters;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class MonsterParser {

    public static List<Monster> parseFromTextFile(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        String[] blocks = content.split("\\R{2,}"); // два и более переводов строки

        List<Monster> monsters = new ArrayList<>();
        int id = 1;

        for (int i = 0; i < blocks.length; i += 2) {
            if (i + 1 >= blocks.length) continue;

            String nameBlock = blocks[i].trim();     // "1. Леший"
            String bodyBlock = blocks[i + 1].trim(); // описание и все параметры

            if (!nameBlock.matches("\\d+\\.\\s.+")) continue;

            Monster m = new Monster();
            m.setId(id++);
            m.setName(nameBlock.replaceFirst("\\d+\\.\\s*", "").trim());

            Matcher desc = Pattern.compile("^(.*?\\.)").matcher(bodyBlock);
            if (desc.find()) m.setDescription(desc.group(1).trim());

            Matcher danger = Pattern.compile("Опасность: (\\d+)").matcher(bodyBlock);
            if (danger.find()) m.setDangerLevel(Integer.parseInt(danger.group(1)));

            Matcher habitat = Pattern.compile("Обитает (.+?)\\.").matcher(bodyBlock);
            if (habitat.find()) m.setHabitat(habitat.group(1).trim());

            Matcher firstMention = Pattern.compile("Первое упоминание: (\\d{4}-\\d{2}-\\d{2})").matcher(bodyBlock);
            if (firstMention.find()) m.setFirstMention(firstMention.group(1).trim());

            Matcher vuln = Pattern.compile("(Уязвим(?:а)? к .*?\\.)|(Неуязвим(?:а)? к .*?\\.)").matcher(bodyBlock);
            if (vuln.find())  m.setVulnerability(vuln.group().trim());

            Matcher imm = Pattern.compile("Иммунитет(?:ы)?: (.+?)\\.").matcher(bodyBlock);
            if (imm.find()) m.setImmunities(imm.group(1).trim());

            Matcher size = Pattern.compile("Рост: (.+?), вес: (.+?)\\.").matcher(bodyBlock);
            if (size.find()) {
                m.setHeight(size.group(1).trim());
                m.setWeight(size.group(2).trim());
            } else {
                Matcher weightOnly = Pattern.compile("Вес (.+?)\\.").matcher(bodyBlock);
                if (weightOnly.find()) m.setWeight(weightOnly.group(1).trim());
            }

            Matcher act = Pattern.compile("Актив[еннао]+ (.+?)\\.").matcher(bodyBlock);
            if (act.find()) m.setActivity(act.group(1).trim());

            Matcher recipe = Pattern.compile("Рецепт(?: яда| масла|)?: (.+?)\\.").matcher(bodyBlock);
            if (recipe.find()) m.setRecipe(recipe.group(1).trim());

            Matcher time = Pattern.compile("Время(?: приготовления|)?:? (\\d+) мин").matcher(bodyBlock);
            if (time.find()) m.setTime(Integer.parseInt(time.group(1)));

            Matcher eff = Pattern.compile("Эффективность: (.+?)\\.").matcher(bodyBlock);
            if (eff.find()) m.setEfficiency(eff.group(1).trim());

            monsters.add(m);
        }

        return monsters;
    }
}