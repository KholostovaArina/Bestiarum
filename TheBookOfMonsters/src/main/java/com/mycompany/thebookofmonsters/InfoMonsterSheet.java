package com.mycompany.thebookofmonsters;

import javax.swing.*;
import java.awt.*;

public class InfoMonsterSheet {

    public static void showInfo(Monster monster, JPanel panel) {
        // Очистка панели перед добавлением новых компонентов
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false); // Делаем панель прозрачной

        // 1. Имя (в верхней части с отступом)
        JLabel nameLabel = new JLabel(monster.getName(), SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(35, 30, 30, 30));

        // 2. Создаем панель для фото, описания и параметров
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Поля вокруг компонентов

        // 3. Фото монстра
        JLabel photoLabel = new JLabel();
        if (Design.getPhotoImage() != null) {
            Image scaledPhoto = Design.getPhotoImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledPhoto));
        }
        photoLabel.setHorizontalAlignment(SwingConstants.LEFT); // Выравнивание по левому краю
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Привязка к верхнему левому углу
        contentPanel.add(photoLabel, gbc);

        // 4. Описание справа от фото
        JTextArea descriptionArea = new JTextArea(monster.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false); // Прозрачный фон

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setOpaque(false); // Делаем JScrollPane прозрачным
        descriptionScrollPane.getViewport().setOpaque(false); // Делаем Viewport прозрачным
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Убираем границы

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Растягиваем описание по ширине
        gbc.fill = GridBagConstraints.BOTH; // Растягиваем по высоте и ширине
        contentPanel.add(descriptionScrollPane, gbc);

        // 5. Параметры под фото и описанием
        String[] parameterNames = {
            "Уровень опасности", "Место обитания", "Первое упоминание",
            "Иммунитеты", "Активность", "Рост", "Вес", "Рецепт",
            "Время приготовления", "Эффективность", "Уязвимость"
        };

        Object[] parameterValues = {
            monster.getDangerLevel(), monster.getHabitat(), monster.getFirstMention(),
            monster.getImmunities(), monster.getActivity(), monster.getHeight(),
            monster.getWeight(), monster.getRecipe(), monster.getTime() + " мин",
            monster.getEfficiency(), monster.getVulnerability()
        };

        JPanel parametersPanel = new JPanel(new GridLayout(0, 1)); // Одна колонка
        parametersPanel.setOpaque(false); // Прозрачный фон

        for (int i = 0; i < parameterNames.length; i++) {
            JLabel label = new JLabel(parameterNames[i] + ": " + parameterValues[i]);
            parametersPanel.add(label);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Занимает две колонки
        gbc.weightx = 0.0; // Отключаем растяжение по ширине
        gbc.fill = GridBagConstraints.NONE; // Не растягиваем
        gbc.anchor = GridBagConstraints.NORTHWEST; // Привязка к верхнему левому углу
        contentPanel.add(parametersPanel, gbc);

        // Добавляем панель с содержимым в основную панель
        panel.add(contentPanel, BorderLayout.CENTER);

        // Применяем шрифты и цвет текста ко всем компонентам
        Design.setFontForAllComponents(panel, new Color(80, 40, 0));
        nameLabel.setFont(Design.getBigFont());

        // Обновляем панель
        panel.revalidate();
        panel.repaint();
    }
}