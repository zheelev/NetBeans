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
import java.awt.EventQueue;
import javax.swing.JFrame;
        
public class SpaceInvaders extends JFrame implements Commons{
    
    public SpaceInvaders()
    {
        initUI();
    }

    public void initUI(){
        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDHT, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
            EventQueue.invokeLater(()->
            {
                SpaceInvaders ex = new SpaceInvaders();
                ex.setVisible(true);
            });
    }
    
}
