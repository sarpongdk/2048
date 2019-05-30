import java.awt.event.*;
import java.awt.*;

public class Controller extends WindowAdapter implements KeyListener, ActionListener
{
   private Model model;
   private View view;

   public Controller()
   {
      model = new Model();
      int[][] grid = model.getGrid();

      view = new View();
      view.updateDisplay(grid);
      view.addKeyPadListener(this);
      view.addNewGameListener(this);
      view.addWindowAdapterListener(this);
   }

   private void updateGame(KeyPressed keyPressed)
   {
      model.updateGrid(keyPressed);
      view.updateDisplay(model.getGrid());
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int code = e.getKeyCode();

      switch (code)
      {
         case KeyEvent.VK_DOWN:
            updateGame(KeyPressed.DOWN);
            break;
   
         case KeyEvent.VK_UP:
            updateGame(KeyPressed.UP);
            break;
         
         case KeyEvent.VK_RIGHT:
            updateGame(KeyPressed.RIGHT);
            break;
         
         case KeyEvent.VK_LEFT:
            updateGame(KeyPressed.LEFT);
            break;
      }
      
      view.updateScore(model.getScore());
   }

   @Override
   public void windowClosing(WindowEvent e)
   {
      System.out.println("Saving game state!");
      model.saveGameState();
   }

   @Override
   public void keyTyped(KeyEvent e) {}
   
   @Override
   public void keyReleased(KeyEvent e) {}

   @Override
   public void actionPerformed(ActionEvent e)
   {
      model.reset();
      view.updateScore(model.getScore());
      view.updateDisplay(model.getGrid());
   }

   public static void main(String[] args)
   {
      Controller controller = new Controller();
   }
}
