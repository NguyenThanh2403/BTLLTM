import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
/*
 * InputManager.java
 *
 * Created on 25 „«—”, 2008, 02:57 „
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



/**
 *
 * @author Mohamed Talaat Saad
 */
public class InputManager implements KeyListener  
{
	 public static int KEY_PRESSED = 0;
	 public static int KEY_RELEASED = 1; 
    
    private Tank tank;
    private Client client;
    /** Creates a new instance of InputManager */
    public InputManager(Tank tank) 
    {
        this.client=Client.getGameClient();
        this.tank=tank;
        
    }

    public void keyTyped(KeyEvent e) {
    	KEY_ACTION(e, this.KEY_RELEASED);
    }
    
    public void keyPressed(KeyEvent e) 
    {
    	KEY_ACTION(e, this.KEY_PRESSED);
    
        
    }

    public void keyReleased(KeyEvent e) {
    	
    	KEY_ACTION(e, this.KEY_RELEASED);
        
        
    }
    
    public  void KEY_ACTION(KeyEvent e, int Event) {
    	if (e.getKeyCode() == KeyEvent.VK_A && Event == KEY_PRESSED) {
    		tank.moveLeft();          
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                        tank.getYposition(),tank.getTankID(),tank.getDirection()));
            
        } else if (e.getKeyCode() == KeyEvent.VK_A && Event == KEY_RELEASED) {
        	tank.notMoveLeft();          
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                        tank.getYposition(),tank.getTankID(),tank.getDirection()));
        }

        if (e.getKeyCode() == KeyEvent.VK_D && Event == KEY_PRESSED) {
        	tank.moveRight();          
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                        tank.getYposition(),tank.getTankID(),tank.getDirection()));
        } else if (e.getKeyCode() == KeyEvent.VK_D && Event == KEY_RELEASED) {
        	tank.notMoveRight();
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                         tank.getYposition(),tank.getTankID(),tank.getDirection()));
        
        }

        if (e.getKeyCode() == KeyEvent.VK_W && Event == KEY_PRESSED) {
        	tank.moveForward();
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                    tank.getYposition(),tank.getTankID(),tank.getDirection()));
        } else if (e.getKeyCode() == KeyEvent.VK_W && Event == KEY_RELEASED) {
        	 tank.notMoveForward();
             client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                     tank.getYposition(),tank.getTankID(),tank.getDirection()));
        }

        if (e.getKeyCode() == KeyEvent.VK_S && Event == KEY_PRESSED) {
        	 tank.moveBackward();
             
             client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                             tank.getYposition(),tank.getTankID(),tank.getDirection()));
                             
        } else if (e.getKeyCode() == KeyEvent.VK_S && Event == KEY_RELEASED) {
        	tank.notMoveBackward();
            
            client.sendToServer(new Protocol().UpdatePacket(tank.getXposition(),
                            tank.getYposition(),tank.getTankID(),tank.getDirection()));
        }
        if (Event == KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_J) {
           
           
            client.sendToServer(new Protocol().ShotPacket(tank.getTankID()));
            tank.shot();
        }
        
    	
    };
}
