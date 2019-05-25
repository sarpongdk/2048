import java.awt.event.*;
import java.awt.*;

public class Controller implements KeyListener
{
   private Model model;
   private View view;

   public Controller()
   {
      model = new Model();
      model.initializeGame();
      int[][] grid = model.getGrid();

      view = new View();
      view.updateDisplay(grid);
      view.addKeyPadListener(this);
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int code = e.getKeyCode();

      switch (code)
      {
         case KeyEvent.VK_DOWN:
            System.out.println("Key Down");
            break;

         case KeyEvent.VK_UP:
            System.out.println("Key Up");
            break;
         
         case KeyEvent.VK_RIGHT:
            System.out.println("Key Right");
            break;
         
         case KeyEvent.VK_LEFT:
            System.out.println("Key Left");
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
