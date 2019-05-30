import java.io.*;

import java.util.*;

enum KeyPressed
{
   UP, DOWN, LEFT, RIGHT
};

public class Model implements Serializable
{
   private final int GRID_SIZE = 4;
   private final Random rand = new Random();
   private final int[] tiles = new int[] {2, 4};
   
   private int score;
   private int[][] grid;

   public Model()
   {
      score = 0;
      grid = new int[GRID_SIZE][GRID_SIZE];

      initGrid();
      initializeGame();
   }

   private void initGrid()
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            grid[i][j] = 0;
         }
      }
   }

   public void saveGameState() 
   {
      ArrayList<Object> data = new ArrayList<Object>();
      data.add(grid);
      data.add(score);

      try
      {
         FileOutputStream fs = new FileOutputStream("../saves/state.ser");
         ObjectOutputStream os = new ObjectOutputStream(fs);
         os.writeObject(data);
         fs.close();
         os.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         return;
      }
   }

   public void loadGameState()
   {
      ArrayList<Object> savedData = new ArrayList<Object>();

      try
      {
         FileInputStream fs = new FileInputStream("../saves/state.ser");
         ObjectInputStream os = new ObjectInputStream(fs);
         savedData = (ArrayList<Object>) os.readObject();
         fs.close();
         os.close();
      }
      catch (IOException e)
      {
         System.err.println("Save file has been corrupted!");
         return;
      }
      catch (ClassNotFoundException c)
      {
         c.printStackTrace();
         return;
      }

      grid = (int[][]) savedData.get(0);
      score = (int) savedData.get(1);
   }

   public int getScore()
   {
      return score;
   }

   public void updateScore()
   {
      score++; // todo: handle score updates
   }

   private void initializeGame()
   {
      generateNewTile();
      generateNewTile();
   }

   public void generateNewTile()
   {
      int i = rand.nextInt(tiles.length);
      int row, col;

      do 
      {
         row = rand.nextInt(GRID_SIZE);
         col = rand.nextInt(GRID_SIZE);
      }
      while (grid[row][col] != 0);
      
      grid[row][col] = tiles[i];
   }

   public void updateGrid(KeyPressed keyPressed)
   {
      switch (keyPressed)
      {
         case UP:
            upUpdate();
            break;

         case DOWN:
            downUpdate();
            break;

         case LEFT:
            leftUpdate();
            break;

         case RIGHT:
            rightUpdate();
            break;
      }

      generateNewTile();
   }

   private void upUpdate()
   {
      for (int i = 1; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            if (grid[i][j] == grid[i-1][j])
            {
               grid[i-1][j] += grid[i][j];
               score += grid[i-1][j];
               grid[i][j] = 0;
            }

            int[] index = clearPath(i, j, KeyPressed.UP);
            grid[index[0]][index[1]] = grid[i][j];
            grid[i][j] = 0;
         }
      }
   }

   private void downUpdate()
   {
      for (int i = GRID_SIZE - 2; i >= 0; i--)
      {   
         for (int j = 0; j < GRID_SIZE; j++)
         {   
            if (grid[i][j] == grid[i+1][j])
            {   
               grid[i+1][j] += grid[i][j];
               score += grid[i+1][j];
               grid[i][j] = 0;
            }

            int[] index = clearPath(i, j, KeyPressed.DOWN);
            grid[index[0]][index[1]] = grid[i][j];
            grid[i][j] = 0;
         }   
      }
   }

   private void leftUpdate()
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {   
         for (int j = 1; j < GRID_SIZE; j++)
         {   
            if (grid[i][j] == grid[i][j - 1])
            {   
               grid[i][j-1] += grid[i][j];
               score += grid[i][j-1];
               grid[i][j] = 0;
            }

            int[] index = clearPath(j, i, KeyPressed.LEFT);
            grid[index[0]][index[1]] = grid[i][j];
            grid[i][j] = 0;
         }   
      }
   }

   private void rightUpdate()
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {   
         for (int j = GRID_SIZE - 2; j >= 0; j--)
         {   
            if (grid[i][j] == grid[i][j+1])
            {   
               grid[i][j+1] += grid[i][j];
               score += grid[i][j+1];
               grid[i][j] = 0;
            }

            int[] index = clearPath(j, i, KeyPressed.RIGHT);
            grid[index[0]][index[1]] = grid[i][j];
            grid[i][j] = 0;
         }   
      }
   }

   private int[] clearPath(int startingIndex, int col, KeyPressed key)
   {
      //boolean clear = true;
      int[] index = new int[2];

      switch (key)
      {
         case UP:
            for (int i = startingIndex - 1; i >= 0; i--)
            {
               if (grid[i][col] != 0)
               {
                  //clear false;
                  index[0] = i + 1;
                  index[1] = col;
                  break;
               }
               else if (i == 0)
               {
                  index[0] = 0;
                  index[1] = col;
               }
            }
            break;

         case DOWN:
            for (int i = startingIndex + 1; i < GRID_SIZE; i++)
            {
               if (grid[i][col] != 0)
               {
                  index[0] = i - 1;
                  index[1] = col;
                  //clear = false;
                  break;
               }
               else if (i == GRID_SIZE - 1)
               {
                  index[0] = GRID_SIZE - 1;
                  index[1] = col;
               }
            }
            break;

         case RIGHT:
            for (int j = startingIndex + 1; j < GRID_SIZE; j++)
            {
               if (grid[col][j] != 0)
               {
                  index[0] = col;
                  index[1] = j - 1;
                  //clear = false;
                  break;
               }
               else if (j == GRID_SIZE - 1)
               {
                  index[0] = col;
                  index[1] = GRID_SIZE - 1;
               }
            }
            break;

         case LEFT:
            for (int j = startingIndex - 1; j >= 0; j--)
            {
               if (grid[col][j] != 0)
               {
                  index[0] = col;
                  index[1] = j + 1;
                  //clear = false;
                  break;
               }
               else if (j == 0)
               {
                  index[0] = col;
                  index[1] = 0;
               }
               
            }
            break;
      }

      return index;
   }

   public int[][] getGrid()
   {
      return grid;
   }

   public void reset()
   {
      score = 0;
      initGrid();
      initializeGame();
   }
}
