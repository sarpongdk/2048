import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class View extends JFrame
{
   private GridPanel panel;

   public View()
   {
      super("2048");
      panel = new GridPanel();

      add(panel);
      pack();
      setResizable(false);
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
      panel.updateDisplay(grid);   
   }

   public void addNewGameListener(ActionListener listener)
   {
      panel.addNewGameListener(listener);
   }

   public void addWindowAdapterListener(WindowAdapter adapter)
   {
      addWindowListener(adapter);
   }

   public static void main(String[] args)
   {
      View view = new View();
   }
}
