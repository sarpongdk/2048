import java.awt.event.*;
import java.awt.*;

public class Controller implements KeyListener
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
            System.out.println("Key Down");
            break;
   
         case KeyEvent.VK_UP:
            System.out.println("Key Up");
            updateGame(KeyPressed.UP);
            break;
         
         case KeyEvent.VK_RIGHT:
            System.out.println("Key Right");
            updateGame(KeyPressed.RIGHT);
            break;
         
         case KeyEvent.VK_LEFT:
            System.out.println("Key Left");
            updateGame(KeyPressed.LEFT);
            break;
      }
   }

   @Override
   public void keyTyped(KeyEvent e) {}
   
   @Override
   public void keyReleased(KeyEvent e) {}

   public static void main(String[] args)
   {
      Controller controller = new Controller();
   }
}
