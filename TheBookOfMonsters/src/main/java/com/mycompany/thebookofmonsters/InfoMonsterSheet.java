package com.mycompany.thebookofmonsters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InfoMonsterSheet {

    private static Monster currentMonster;
    public static void showInfo(Monster monster, JPanel panel) {

        currentMonster = monster;
//        JPanel currentInfoPanel = panel;

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JLabel nameLabel = new JLabel(monster.getName(), SwingConstants.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 55, 35, 35));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Фото монстра (оставляем как было)
        JLabel photoLabel = new JLabel();
        if (Design.getPhotoImage() != null) {
            Image scaledPhoto = Design.getPhotoImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledPhoto));
        }
        photoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPanel.add(photoLabel, gbc);

        // Описание (оставляем JTextArea, но с вашими отступами)
        JTextArea descriptionArea = new JTextArea("\n\n" + monster.getDescription());
        descriptionArea.setEditable(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false);

        // Слушатель изменений описания
        descriptionArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDescription();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDescription();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDescription();
            }

            private void updateDescription() {
                currentMonster.setDescription(descriptionArea.getText().replace("\n\n", ""));
            }
        });

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setOpaque(false);
        descriptionScrollPane.getViewport().setOpaque(false);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder());

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(descriptionScrollPane, gbc);

        // Параметры (полностью возвращаем ваш исходный вариант с JLabel)
        String[] parameterNames = {
            "Уровень опасности", "Место обитания", "Первое упоминание",
            "Иммунитеты", "Активность", "Рост", "Вес", "Рецепт",
            "Время приготовления", "Эффективность", "Уязвимость"
        };

        Object[] parameterValues = {
            monster.getDangerLevel(), monster.getHabitat(), monster.getFirstMention(),
            monster.getImmunities(), monster.getActivity(), monster.getHeight(), monster.getWeight(),
            monster.getRecipe(),
            monster.getTime() + " мин", monster.getEfficiency(), monster.getVulnerability()
        };

        JPanel parametersPanel = new JPanel(new GridLayout(0, 1));
        parametersPanel.setOpaque(false);

        for (int i = 0; i < parameterNames.length; i++) {
            if (i == 7) { // Рецепт
                String[] recipe = monster.getRecipe().split(",");
                for (int j = 0; j < recipe.length; j++) {
                    JLabel label = new JLabel();
                    if (j == 0) {
                        label.setText(parameterNames[i] + ": " + recipe[j]);
                    } else {
                        label.setText(recipe[j]);
                    }
                    parametersPanel.add(label);
                }
            } else {
                // Создаем панель для каждого параметра с JLabel и скрытым JTextField
                JPanel paramPanel = new JPanel(new BorderLayout());
                paramPanel.setOpaque(false);

                JLabel label = new JLabel(parameterNames[i] + ": " + parameterValues[i]);
                paramPanel.add(label, BorderLayout.WEST);

                // Скрытое поле для редактирования
                JTextField editField = new JTextField(parameterValues[i].toString());
                editField.setVisible(false);
                editField.setOpaque(false);
                editField.setBorder(BorderFactory.createEmptyBorder());
                
                final int index = i;
                // Двойной клик для редактирования
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            label.setVisible(false);
                            editField.setVisible(true);
                            editField.setText(label.getText().replace(parameterNames[index] + ": ", ""));
                            editField.requestFocus();
                        }
                    }
                });

                
                // Сохранение при потере фокуса или нажатии Enter
                editField.addActionListener(e -> {
                    updateParameter(index, editField.getText());
                    label.setText(parameterNames[index] + ": " + editField.getText());
                    editField.setVisible(false);
                    label.setVisible(true);
                });

                editField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        updateParameter(index, editField.getText());
                        label.setText(parameterNames[index] + ": " + editField.getText());
                        editField.setVisible(false);
                        label.setVisible(true);
                    }
                });

                paramPanel.add(editField, BorderLayout.CENTER);
                parametersPanel.add(paramPanel);
            }
        }

        // Добавляем пустые строки (как у вас было)
        parametersPanel.add(new JLabel(""));
        parametersPanel.add(new JLabel(""));
        parametersPanel.add(new JLabel(""));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPanel.add(parametersPanel, gbc);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        Design.setFontForAllComponents(panel);
        nameLabel.setFont(Design.getBigFont());

        panel.revalidate();
        panel.repaint();
    }

    private static void updateParameter(int paramIndex, String newValue) {
        switch (paramIndex) {
            case 0 -> currentMonster.setDangerLevel(Integer.parseInt(newValue));
            case 1 -> currentMonster.setHabitat(newValue);
            case 2 -> currentMonster.setFirstMention(newValue);
            case 3 -> currentMonster.setImmunities(newValue);
            case 4 -> currentMonster.setActivity(newValue);
            case 5 -> currentMonster.setHeight(newValue);
            case 6 -> currentMonster.setWeight(newValue);
            case 7 -> currentMonster.setRecipe(newValue);
            case 8 -> currentMonster.setTime(Integer.parseInt(newValue.replace(" мин", "")));
            case 9 -> currentMonster.setEfficiency(newValue);
            case 10 -> currentMonster.setVulnerability(newValue);
        }
    }
}
