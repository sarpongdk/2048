import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel 
{
   private final int GRID_SIZE = 4;

   private JButton[][] grid;
   private JLabel score;
   private JPanel scorePanel;
   private JPanel gridPanel;

   public GridPanel()
   {
      super();
      
      gridPanel = new JPanel();
      gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, 15, 15));
      gridPanel.setBackground(new Color(204, 206, 204));
      scorePanel = new JPanel();
      scorePanel.setLayout(new BorderLayout());
      scorePanel.setBackground(new Color(204, 206, 204));
      score = new JLabel("Score: 2048");
      scorePanel.add(score);

      grid = new JButton[4][4];
      setLayout(new BorderLayout());

      addButtons();
      add(gridPanel, BorderLayout.CENTER);
      add(scorePanel, BorderLayout.SOUTH); // todo: add score panel to the bottom
      setBackground(new Color(204, 206, 204));
   }

   public void updateScore(int score)
   {
      this.score.setText("Score: " + Integer.toString(score));
   }

   public void updateDisplay(int[][] grid)
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            this.grid[i][j].setText(Integer.toString(grid[i][j]));
         }
      }
   }

   private void addButtons()
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            grid[i][j] = new JButton();
            grid[i][j].setPreferredSize(new Dimension(150, 150));
            gridPanel.add(grid[i][j]);
         }
      }
   }

   public void addKeyPadListener(KeyListener listener)
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            grid[i][j].addKeyListener(listener); 
         }
      }
   }

   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setLayout(new BorderLayout());
      GridPanel panel = new GridPanel();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.add(panel, BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);
   }
}
