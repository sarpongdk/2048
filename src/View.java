import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class View extends JFrame
{
   private GridPanel panel;

   public View()
   {
      panel = new GridPanel();

      add(panel);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public void addKeyPadListener(KeyListener listener)
   {
      panel.addKeyPadListener(listener);
   }

   public void updateScore(int score)
   {
      panel.updateScore(score);
   }

   public void updateDisplay(int[][] grid)
   {
   
   }

   public static void main(String[] args)
   {
      View view = new View();
   }
}
