import java.util.Random;

enum KeyPressed
{
   UP, DOWN, LEFT, RIGHT
};

public class Model
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
      generateTileValue();
      generateTileValue();
   }

   public void generateTileValue()
   {
      int i = rand.nextInt(tiles.length);

      int row = rand.nextInt(GRID_SIZE);
      int col = rand.nextInt(GRID_SIZE);
      
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
   }

   private void upUpdate()
   {
      for (int i = 1; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            if (grid[i][j] == grid[i-1][j])
            {
               grid[i - 1][j] += grid[i][j];
               grid[i][j] = 0;
            }
            else if (grid[i-1][j] == 0)
            {
               grid[i-1][j] = grid[i][j];
               grid[i][j] = 0;
            }
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
               grid[i + 1][j] += grid[i][j];
               grid[i][j] = 0;
            }   
            else if (grid[i+1][j] == 0)
            {   
               grid[i-1][j] = grid[i][j];
               grid[i][j] = 0;
            }   
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
               grid[i][j - 1] += grid[i][j];
               grid[i][j] = 0;
            }   
            else if (grid[i][j - 1] == 0)
            {   
               grid[i][j - 1] = grid[i][j];
               grid[i][j] = 0;
            }   
         }   
      }
   }

   private void rightUpdate()
   {
      for (int i = 0; i < GRID_SIZE; i++)
      {   
         for (int j = GRID_SIZE - 2; j >= 0; j--)
         {   
            if (grid[i][j] == grid[i][j + 1])
            {   
               grid[i][j + 1] += grid[i][j];
               grid[i][j] = 0;
            }   
            else if (grid[i][j + 1] == 0)
            {   
               grid[i][j + 1] = grid[i][j];
               grid[i][j] = 0;
            }   
         }   
      }
   }

   public int[][] getGrid()
   {
      return grid;
   }
}
