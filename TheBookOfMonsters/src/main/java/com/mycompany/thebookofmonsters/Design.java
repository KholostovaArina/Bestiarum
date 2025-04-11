package com.mycompany.thebookofmonsters;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Design {
    private static Font bigFont;
    static {
        try (InputStream fontStream = Design.class.getResourceAsStream("/BigFont.ttf")) {
            bigFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            bigFont = bigFont.deriveFont(24f);
        } catch (IOException | FontFormatException e) {
            bigFont = new Font("Serif", Font.PLAIN, 22);
        }
    }

    private static Font font;
    static {
        try (InputStream fontStream = Design.class.getResourceAsStream("/cuteFont.ttf")) {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = font.deriveFont(18f);
        } catch (IOException | FontFormatException e) {
            font = new Font("Serif", Font.PLAIN, 18);
        }
    }

    private static Image bookImage;
    private static Image photoImage;
    static {
        try {
            bookImage = ImageIO.read(Design.class.getResourceAsStream("/книга.jpg"));
            photoImage = ImageIO.read(Design.class.getResourceAsStream("/фото.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Font getBigFont() {
        return bigFont;
    }

    public static Font getFont() {
        return font;
    }

    public static Image getBookImage() {
        return bookImage;
    }

    public static Image getPhotoImage() {
        return photoImage;
    }

    public static void setFontForAllComponents(Container container) {
        for (Component component : container.getComponents()) {
            component.setFont(font);
            component.setForeground( new Color(80, 40, 0));
            
            // Используем старый синтаксис для instanceof
            if (component instanceof Container) {
                Container container1 = (Container) component;
                setFontForAllComponents(container1);
            }
        }
    }

    public static void applyBackgrounds(JScrollPane panel) {
        if (bookImage == null) {
            System.out.println("Изображение bookImage не загружено.");
            return;
        }

        // Создаем пользовательский JPanel для отрисовки фона
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Растягиваем изображение на весь размер панели
                g.drawImage(bookImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false); // Прозрачность фона

        // Получаем текущее содержимое JScrollPane
        Component view = panel.getViewport().getView();

        // Если содержимое есть, добавляем его на нашу фоновую панель
        if (view != null) {
            backgroundPanel.add(view, BorderLayout.CENTER);
        }

        // Заменяем содержимое JScrollPane на нашу фоновую панель
        panel.setViewportView(backgroundPanel);

        // Делаем JScrollPane и его Viewport прозрачными
        panel.setOpaque(false);
        panel.getViewport().setOpaque(false);
    }
}
