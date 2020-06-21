import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class PlaneEnemy {
	 private Image enemyImg;
	    private BufferedImage enemyBuffImage;
	    Random generator = new Random();
	    private int xPosi;
	    private int yPosi;
	    private int ID;
	    public boolean stop=false;
	    private float velocityX=0.05f,velocityY=0.05f;
	    public PlaneEnemy(int x,int y) {
	        final SimpleSoundPlayer sound_boom =new SimpleSoundPlayer("boom.wav");
	        final InputStream stream_boom =new ByteArrayInputStream(sound_boom.getSamples());
	        xPosi=generator.nextInt(12)*50;
	        yPosi=300;
	        ID = y;
	        stop=false;
	        enemyImg=new ImageIcon("Images/enemyplane.png").getImage();
	        
	        enemyBuffImage=new BufferedImage(enemyImg.getWidth(null),enemyImg.getHeight(null),BufferedImage.TYPE_INT_RGB);
	        enemyBuffImage.createGraphics().drawImage(enemyImg,0,0,null);
	        Thread t= new Thread(new Runnable() {
	        public void run() {
	            sound_boom.play(stream_boom);
	        }
	    }); 
	    t.start();
	    }
	    public int getPosiX() {
	        return xPosi;
	    }
	    public int getPosiY() {
	        return yPosi;
	    }
	    public int getID() {
	    	return ID;
	    }
	    public void setID(int id) {
	    	ID = id;
	    }
	    public void setPosiX(int x) {
	        xPosi=x;
	    }
	    public void setPosiY(int y) {
	        yPosi=y;
	    }
	    public BufferedImage getBomBufferdImg() {
	        return enemyBuffImage;
	    }
	    
	    public BufferedImage getBombBuffImage() {
	        return enemyBuffImage;
	    }
	    
	    public boolean checkCollision() 
	    {
	        ArrayList<Tank>clientTanks=GameBoardPanel.getClients();
	        int x,y;
	        for(int i=1;i<clientTanks.size();i++) {
	            if(clientTanks.get(i)!=null) {
	                x=clientTanks.get(i).getXposition();
	                y=clientTanks.get(i).getYposition();
	                
	                if((yPosi>=y&&yPosi<=y+43)&&(xPosi>=x&&xPosi<=x+43)) 
	                {
	                    
	                    ClientGUI.setScore(50);
	                    
	                    ClientGUI.gameStatusPanel.repaint();
	                    
	                    try {
	                        Thread.sleep(200);
	                    } catch (InterruptedException ex) {
	                        ex.printStackTrace();
	                    }
	                    if(clientTanks.get(i)!=null)
	                     Client.getGameClient().sendToServer(new Protocol().RemoveClientPacket(clientTanks.get(i).getTankID()));  
	                    
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

}




