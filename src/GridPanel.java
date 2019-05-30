import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel 
{
   private final int GRID_SIZE = 4;
   private final String FONT = "Arial";
   private final int FONT_SIZE = 26;
   private final Color BG_COLOR = new Color(0xbbada0);

   private JButton[][] grid;
   private JLabel score;
   private JPanel scorePanel;
   private JPanel gridPanel;
   private JButton newGame;
   private ColorPicker colorPicker;

   public GridPanel()
   {
      super();
      
      colorPicker = new ColorPicker();
      gridPanel = new JPanel();
      gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, 15, 15));
      gridPanel.setBackground(BG_COLOR);
      scorePanel = new JPanel();
      scorePanel.setLayout(new BorderLayout());
      scorePanel.setBackground(BG_COLOR);
      score = new JLabel("Score: 0");
      newGame = new JButton("New Game");
      scorePanel.add(score);
      scorePanel.add(newGame, BorderLayout.LINE_END);

      grid = new JButton[4][4];
      setLayout(new BorderLayout());

      addButtons();
      add(gridPanel, BorderLayout.CENTER);
      add(scorePanel, BorderLayout.SOUTH); // todo: add score panel to the bottom
      setBackground(BG_COLOR);
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
            if (grid[i][j] != 0)
            {
               int val = grid[i][j];
               String str = Integer.toString(val);
               this.grid[i][j].setText(str);
            }
            else
            {
               this.grid[i][j].setText("");
            }

            String key = this.grid[i][j].getText();
            Color color = colorPicker.getColor(key);
            this.grid[i][j].setBackground(color);
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
            grid[i][j].setOpaque(true);
            // grid[i][j].setContentAreaFilled(false);
            grid[i][j].setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
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

   public void addNewGameListener(ActionListener listener)
   {
      newGame.addActionListener(listener);
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
