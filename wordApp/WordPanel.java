package skeletonCodeAssgnmt2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable 
{
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
		private WordControl wordControl;

		
		public void paintComponent(Graphics g) 
		{
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		   
		  }
		
		WordPanel(WordRecord[] words, int maxY, WordControl wControl) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;	
			this.wordControl = wControl;	
		}
		
		public void run() 
		{
			wordControl.wordStart();

			while(!wordControl.ended()){
				if (wordControl.updates()){
					repaint();
					wordControl.setNotUpdated();
				}

				try{
					Thread.sleep(2);
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			//add in code to animate this
		}
		
		public void repaintOnce()
		{
				repaint();
		}
	

}


