import java.util.Random;

enum State
{
   GAME_OVER, PLAY
};

public class Model
{
   private final int GRID_SIZE = 4;
   private final Random rand = new Random();
   private final int[] tiles = new int[] {2, 4};
   
   private int score;
   private State state;
   private int[][] grid;

   public Model()
   {
      score = 0;
      state = State.PLAY;
      grid = new int[GRID_SIZE][GRID_SIZE];
   }

   public int getScore()
   {
      return score;
   }

   public void updateScore()
   {
      score++; // todo: handle score updates
   }

   public void changeState()
   {
      state = State.GAME_OVER;
   }

   public void initializeGame()
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

   public int[][] getGrid()
   {
      return grid;
   }
}
