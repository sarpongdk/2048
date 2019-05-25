import java.util.HashMap;

import javax.swing.*;

import java.awt.Color;

public class ColorPicker
{
   private HashMap<String, Color> colors;

   public ColorPicker()
   {
      colors = new HashMap<>();

      initColors();
   }

   private void initColors()
   {
      colors.put("", new Color(0xcdc1b4));
      colors.put("2", new Color(0xeee4da));
      colors.put("4", new Color(0xede0c8));
      colors.put("8", new Color(0xf2b179));
      colors.put("16", new Color(0xf59563));
      colors.put("32", new Color(0xf67c5f));
      colors.put("64", new Color(0xf65e3b));
      colors.put("128", new Color(0xedcf72));
      colors.put("256", new Color(0xedcc61));
      colors.put("512", new Color(0xedc850));
      colors.put("1024", new Color(0xedc53f));
      colors.put("2048", new Color(0xedc22e));
   }

   public Color getColor(String key)
   {
      return colors.get(key);
   }

   public static void main(String[] args)
   {
      ColorPicker picker = new ColorPicker();
      JFrame frame = new JFrame();
      JButton button = new JButton("2");

      String text = button.getText();
      Color color = picker.getColor(text);
      button.setBackground(color);

      frame.add(button);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
