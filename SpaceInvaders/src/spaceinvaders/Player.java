/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author Admin
 */
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons{
    
    private final int START_Y = 280;
    private final int START_X = 270;
    
    private final String playerImg = "src/images/player.png";
    private int widht;
    
    public Player()
    {
        initPlayer();
    }

    private void initPlayer() {
        
        ImageIcon  ii = new ImageIcon(playerImg);
        widht = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
     
    }
    
    public void act()
    {
        x += dx;
        
        if(x <= 2)
        {
            x = 2;
        }
        
        if(x >= BOARD_WIDHT -2 * widht)
        {
            x = BOARD_WIDHT -2 * widht;
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            dx = -2;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}
