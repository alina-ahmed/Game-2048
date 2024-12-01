

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game8by8 extends GAME {
	
	List<Rectangle> rectangles = new ArrayList<Rectangle>();
	private String Path;//new
	private String fileName = "HIGHSCORE.txt";//new
	
	
	
	Game8by8()
	{
		super(8,8);
		setFocusable(true);
		 addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                switch (e.getKeyCode()) {
	                    case KeyEvent.VK_UP:
	                    	ArrowUp();
	                    	SumUp();
	                    	continueGame();
	                        break;
	                    case KeyEvent.VK_DOWN:
	                    	ArrowDown();
	                    	SumDown();
	                    	continueGame();
	                        break;
	                    case KeyEvent.VK_LEFT:
	                   	ArrowLeft();
	                    	SumLeft();
	                    	continueGame();
	                        break;
	                    case KeyEvent.VK_RIGHT:
	                   	ArrowRight();
	                    	 SumRight();
	                    	 continueGame();
	                        break;
	                    case KeyEvent.VK_ESCAPE:
	                    	System.exit(1);
	                    	break;
	                }
	                repaint();
	            }
	           
	        });
		
		
		 try //new
			{
				Path= GAME.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				
				
			}
			catch(Exception e)//new
			{
				e.printStackTrace();
			}
			
			viewHighScore();//new
	}
	
	
	//method that will create file
	 public void createFile() //new
 	{
 		try 
 		{
 			File newfile = new File(Path,fileName);
 			FileWriter file = new FileWriter (newfile);
 			BufferedWriter enter = new BufferedWriter(file);
 			enter.write(""+0);
 			enter.close();
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		
 	}
 	
	 
	 //method to view high score
 	public void viewHighScore() //new
 	{
 		try
 		{
 			File file = new File(Path,fileName);	
 			if(!file.isFile())
 			{
 				createFile();
 			}
 			
 			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
 			highScore = (Integer.parseInt(read.readLine()));
 			
 			
 			read.close();
 			
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 	}
 	
 	
 	//method to set high score
 	public void HighScoreSetter()//new
 	{
 		FileWriter fw = null;
 		try
 		{
 			File f = new File(Path,fileName);
 			fw = new FileWriter(f);
 			BufferedWriter write = new BufferedWriter(fw);
 			
 			if (score>=highScore)
 				{write.write(""+score);
 				write.close();
 				highScore=score;
 				}
 			else
 			{
 				write.close();
 			}
 			
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 	}
 	
 	
 	
	public void paintComponent(Graphics gg)
	{
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
        if(start==true)
           
        {  drawScoreBar(g);
        drawGrid(g);
         }
        if(lost==true)
     	   drawEnd(g);
        
     }
    
	//create reset bar
	public void drawResetBar(Graphics2D g)
	{
		g.setColor(new Color(0xfff4d3));
		g.fillRect(90, 10, 140, 36);
		g.setColor(Color.black);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Press esc to quit",112,35);
			
	}
	
	
	
	public void drawScoreBar(Graphics2D g)
	{
		g.setColor(new Color(0xfff4d3));
		
			g.fillRect(500, 10, 130, 36);
			g.setColor(Color.black);
			String str=String.valueOf(score);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("     SCORE: " +str,500,35);
			
			
			
					g.setColor(new Color(0xfff4d3));
					g.fillRect(330, 10, 165, 36);
					g.setColor(Color.black);
					String SC=String.valueOf(highScore);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					g.drawString("HIGH SCORE: " +SC,338,36);
		
	
	}
	
	
	public void drawEnd(Graphics2D g)
	{
		 g.setColor(new Color(0x701710));
	       
	        g.setBackground(Color.gray);
	      //  g.drawRect(0, 0, 800, 800);
	        g.setFont(ft);
	        g.setColor(Color.black);
	        
	        g.drawString("You lose", 300, 100);
	       
	        for (Rectangle r: rectangles)
	        {
	     	   g.setColor(new Color(0xffc4c3));
	            g.fillRect(r.x-100, r.y+100, r.width+200, r.height+200);
	            g.setColor(Color.black);
	            g.setFont(ft);
	           g.drawString("Click to start again", 300,375);
	        
	        }
	}
	
	
	public void drawGrid(Graphics2D g)
	
	{
		
		g.setColor(new Color(0xfff4d3));
		g.fillRect(100, 10, 200, 40);
		g.setColor(Color.black);
		String SC=String.valueOf(highScore);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("  Press esc to quit",130,32);

		
		Color gridColor = new Color(0xBBADA0);
		Color emptyColor = new Color(0xCDC1B4);
		if(start==false)
		{
			g.setColor(new Color(0xfff4d3));
			//change
			g.fillRect(70, 70, 70, 70);
		}
		else {
		g.setColor(gridColor);
		g.fillRoundRect(60, 60, 570, 570, 15, 15);
     
            for (int r = 1; r <= 8; r++) {
                for (int c = 1; c <= 8; c++) {
                        g.setColor(emptyColor);
                        g.fillRoundRect(c * 70, r*70, 60, 60, 5, 5);
                    }
                }
		
		if(count!=0)
		{
			 for (int r = 1; r <= 8; r++) {
	                for (int c = 1; c <= 8; c++) {
	                	t[r-1][c-1]=new tile(m[r-1][c-1]);            
	                        drawtile(g,r,c);
	                    }
	                }
		} 
		}
		if(lost==true)
		{
			   g.setBackground(Color.gray);
		        g.drawRect(0, 0, 800, 800);
		        g.setFont(ft);
		        g.setColor(Color.black);
		        
		        g.drawString("You lose", 300, 100);
		       
		        for (Rectangle r: rectangles)
		        {
		     	   g.setColor(new Color(0xffc4c3));
		            g.fillRect(r.x-100, r.y+100, r.width+200, r.height+200);
		            g.setColor(Color.black);
		            g.setFont(ft);
		           g.drawString("Click to start again", 300,375);
		        
		        }
			}
	      
		}
		
	
	
	public void drawtile(Graphics2D g,int r, int c)
	{
		Color TileColor = t[r-1][c-1].setColor();
				
		int value = t[r-1][c-1].getValue();
       g.setColor(TileColor);
        g.fillRoundRect(c * 70, r*70, 60, 60, 5, 5);
        
       g.setColor(new Color(0x701710));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();
        String S = String.valueOf(value);
        int x = c * 70 + (70 - fm.stringWidth(S)) / 2;
        int y = r * 70 + (asc + (70 - (asc + dec)) / 2);
       
        if(value!=0)
       
        g.drawString(S, x, y);

        
	}
	
	
	public static void main(String []args)
	{
		 JFrame F = new JFrame();
	        F.setSize(800, 800);
	        F.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        F.setTitle("2048");
	        F.setResizable(true);
	        F.add(new Game8by8(), BorderLayout.CENTER);
	        F.setLocationRelativeTo(null);
	        F.setVisible(true);
	
	}
	

	
}
