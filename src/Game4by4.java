



	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.FontMetrics;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JFrame;

	public class Game4by4 extends GAME {
		 private String Path;//new
			private String fileName = "HIGHSCORE.txt";//new
		
		Game4by4()
		{
			super(4,4);
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
		 	
		 	 void stopGame()
			 {
				 if(score>=highScore)
				 { HighScoreSetter();}
				 else{}//new
				 lost=true;
			 }
			
		 	 
		public void paintComponent(Graphics gg)
		{
	        super.paintComponent(gg);
	        Graphics2D g = (Graphics2D) gg;
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	       
	          drawResetBar(g);
	        drawScoreBar(g);
	        drawGrid(g);
	    }
		public void drawResetBar(Graphics2D g)
		{
			g.setColor(new Color(0xfff4d3));
			g.fillRect(30, 20, 153, 37);
			g.setColor(Color.black);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("Press esc to quit",50,41);
				
		}
		
		public void drawScoreBar(Graphics2D g)
		{
			g.setColor(new Color(0xfff4d3));
			
				g.fillRect(420, 20, 130, 36);
				g.setColor(Color.black);
				String str=String.valueOf(score);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				g.drawString("     SCORE: " +str,420,46);
				
				
				
						g.setColor(new Color(0xfff4d3));
						g.fillRect(250, 20, 160, 36);
						g.setColor(Color.black);
						String SC=String.valueOf(highScore);
						g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
						g.drawString("HIGH SCORE: " +SC,260,46);
			
		
		}
		public void drawGrid(Graphics2D g)
		
		{
			
			Color gridColor = new Color(0xBBADA0);
			Color emptyColor = new Color(0xCDC1B4);
			if(start==false)
			{
				g.setColor(new Color(0xfff4d3));
				g.fillRect(100, 100, 100, 100);
			}
			else {
			g.setColor(gridColor);
	        g.fillRoundRect(90, 90, 400, 400, 12, 12);
	            for (int r = 1; r <= 4; r++) {
	                for (int c = 1; c <= 4; c++) {
	                        g.setColor(emptyColor);
	                        g.fillRoundRect(c * 100, r*100, 80, 80, 7, 7);
	                    }
	                }
			
			if(count!=0)
			{
				 for (int r = 1; r <= 4; r++) {
		                for (int c = 1; c <= 4; c++) {
		                	t[r-1][c-1]=new tile(m[r-1][c-1]);            
		                        drawtile(g,r,c);
		                    }
		                }
			} 
			}
			if(lost==true)
			{
				 g.setColor(new Color(0x701710));
		        g.fillRoundRect(90, 90, 10, 10, 12, 12);
		        g.setFont(new Font("Cooper Black", Font.BOLD, 100));
		        g.drawString("You lose", 60, 300);
			}
			 if(won==true)
		       {
				 g.setColor(new Color(0x701710));
			        g.fillRoundRect(90, 90, 10, 10, 12, 12);
			        g.setFont(new Font("Cooper Black", Font.BOLD, 100));
			        g.drawString("You win", 60, 300);
		       }
		}
		
		
		public void drawtile(Graphics2D g,int r, int c)
		{
			Color TileColor = t[r-1][c-1].setColor();
					
			int value = t[r-1][c-1].getValue();
	       g.setColor(TileColor);
	        g.fillRoundRect(c * 100, r*100, 80, 80, 7, 7);
	        
	       g.setColor(new Color(0x701710));
	        FontMetrics fm = g.getFontMetrics();
	        int asc = fm.getAscent();
	        int dec = fm.getDescent();
	        String S = String.valueOf(value);
	        int x = c * 100 + (80 - fm.stringWidth(S)) / 2;
	        int y = r * 100 + (asc + (80 - (asc + dec)) / 2);
	       
	        if(value!=0)
	       
	        g.drawString(S, x, y);

	        
		}
		
		
		

		
	}
