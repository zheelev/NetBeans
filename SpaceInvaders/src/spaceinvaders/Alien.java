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
import javax.swing.ImageIcon;

public class Alien extends Sprite{
    
    private Bomb bomb;
    private final String alienImg = "src/images/alien.png";
    
    public Alien(int x, int y)
    {
        initAlien(x,y);
    }

    private void initAlien(int x, int y) {
        
        this.x = x;
        this.y = y;
        
        bomb = new Bomb(x,y);
        ImageIcon ii = new ImageIcon(alienImg);
        setImage(ii.getImage());
    }
    
    public void act(int derection)
    {
        this.x += derection;
    }
    public Bomb getBomb()
    {
        return bomb;
    }
    
    public class Bomb extends Sprite{
        
        private final String bombImg = "src/images/bomb.png";
        private boolean destroyed;
        
        public Bomb(int x, int y){
            initBomb(x,y);
        }

        private void initBomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
            
        }
        
        public void setDestroyed(boolean destroyed)
        {
            this.destroyed = destroyed;
        }
        public boolean isDestroyed()
        {
            return destroyed;
        }
    }
}

