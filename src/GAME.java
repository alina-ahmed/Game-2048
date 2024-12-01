


import java.awt.BorderLayout;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import javax.swing.*;





public class GAME extends JPanel implements MouseListener {
	int X,Y;
	tile t[][];
	int m[][];
	int score;
	int highScore=0;
	boolean won;
	boolean lost=false;
	int side=4;
	boolean start=false;
	boolean running=false;
	Font f=new Font("Arial",Font.BOLD,32);
	Font ft=new Font("Arial",Font.BOLD,64);
	int checkMoveable;
	Random random=new Random();
	int count=0;
	List<Rectangle> rectangles = new ArrayList<Rectangle>();  //arraylist
	static JRadioButton r1,r2,r3,r4;
	JFrame j;
	 

		
	//constructor			
	GAME()
	{
		
		setPreferredSize(new Dimension(340, 400));
        setBackground(Color.lightGray);
        setFont(f);
        setFocusable(true);
      
        rectangles.add( new Rectangle(200, 300, 350, 100) );
        
        //create JRadioButton
        r1=new JRadioButton("8×8");
	      r1.setBounds(340,290,100,30); 
	      r2=new JRadioButton("4×4"); 
	      r2.setBounds(340,200,100,30); 
	      r3=new JRadioButton("6×6"); 
	      r3.setBounds(340,260,100,30); 
	      r4=new JRadioButton("5×5"); 
	      r4.setBounds(340,230,100,30);    
	      ButtonGroup bg=new ButtonGroup();    
	      bg.add(r1);   bg.add(r2);  
	      bg.add(r3);   bg.add(r4); 
	      add(r1);    add(r2);
	      add(r3);    add(r4);
	      
	      
	      //implement Mouselistener
        addMouseListener(this);
        
        }
        
	
	//constructor
        GAME(int X,int Y)
        {
        	
        	this.X=X;
    		this.Y=Y;
    		t=new tile[X][Y];
    		m=new int[X][Y];
    		start();
    		
    		
        
       
	}
        
     //method to check if the tiles are moveable or not
        public boolean checkMoveable()
   	 {
   		int NoOfTiles=0;
   		
   		for(int x=0;x<X;x++)
   		{
   			for (int y=0;y<Y;y++)
   			{
   				if(x<(X-1))
   				{
   					if (y<(Y-1))
   					{
   						if(m[x][y]!=m[x][y+1] && m[x][y]!=m[x+1][y] )
   						{
   							NoOfTiles++;
   						}
   						else
   						{
   							
   						}
   					}
   					else
   					{
   						if(m[x][y]!=m[x+1][y])
   						{
   							NoOfTiles++;
   						}
   						else
   						{
   							
   						}
   					}
   				}
   			    else
   				{
   			    	if(y<(Y-1))
   			    	{
   			    		if(m[x][y]!=m[x][y+1])
   						{
   							NoOfTiles++;
   						}
   						else
   						{
   							
   						}
   			    		
   			    	}
   			    	else
   			    	{
   			    		if(m[x][y]!=m[x][y-1])
   						{
   							NoOfTiles++;
   						}
   						else
   						{
   							
   						}
   			    	}
   				}
   			}
   		}
   		
   		if (NoOfTiles==(X*Y))
   			return false;
   		else 
   			return true;
   	 }
   	
        
        
        
        //method continueGame
        public void continueGame()
        {
        	boolean checkFilled = false;
        	int checkVal=0;
        	int checkFalse;
        	if (checkMoveable()==false)
			checkFalse=1;
	
        	for(int x=0;x<X;x++)
		{
			for (int y=0;y<Y;y++)
			{
				if(m[x][y]!=0)
					checkVal++;
				if(m[x][y]==2048)
					won=true;
					
			}
			
		}
		if (checkVal==16)
			checkFilled=true;
		
		if(checkMoveable()==false && checkFilled==true)
		stopGame();
		else if(checkMoveable()==true && checkFilled==true)
		{
		
		}
		else if(checkMoveable()==true && checkFilled==false)
		{
		addRandomTile(	);
		}
		else if(checkMoveable()==false && checkFilled==false)
		{
		addRandomTile();
		}


}
        
       //Method to randomly add tiles at the start of game
	 private void addRandomTile() {
			int check=0;
			do{
		 int row=(int)(Math.random()*4);
		 int col=(int)(Math.random()*4);
		 int val =  Math.random() < 0.9 ? 2 : 4;
		 if(m[row][col]==0)
		 {
			t[row][col] = new tile(val);
	        m[row][col]=t[row][col].getValue();
	        check++;
	      
		 }
			}
			while(check==0);
		 
			
	    }
	 
	 //method called if game is stoped
	 void stopGame()
	 {
		
		 lost=true;
	 }
	
	 
	 //method to start the game
	public void start()
	{
		start=true;
		score=0;
		t=new tile[X][Y];
		for(int x=0;x<X;++x)
		{
			for(int y=0;y<Y;++y)
			{
				t[x][y]=new tile(0);
			}
		}
		for(int x=0;x<X;++x)
		{
			for(int y=0;y<Y;++y)
			{
				m[x][y]=0;
			}
		}
		
		addRandomTile();
		addRandomTile();
		
		if(X==8 && Y==8)
		{
			addRandomTile();
			addRandomTile();
		}
		
		count++;
		
		
		
	}
	
	
	//paint method
	public void paintComponent(Graphics gg)
	{
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       if(start==false)
    	 
       {    
    	   drawMenu(g);
       
       
       for (Rectangle r: rectangles)
       {
    	   g.setColor(new Color(0xffc4c3));
           g.fillRect(r.x, r.y, r.width, r.height);
           g.setColor(Color.black);
           g.setFont(ft);
          g.drawString("Start", 300,375);
       
       }
       }
       if(start==true)
          
       {  drawScoreBar(g);
       drawGrid(g);
        }

       
      
    }
	
	
	//method which is drawing menu
	public void drawMenu(Graphics2D g)
	{

		g.setColor(new Color(0xfff4d3));
		g.setColor(Color.BLACK);
		g.setFont(ft);
		g.drawString("Game 2048",200, 200);
		
	}
	
	
	//method creating scorebar
	public void drawScoreBar(Graphics2D g)
	{
		g.setColor(new Color(0xfff4d3));
		
			g.fillRect(50, 20, 200, 50);
			g.setColor(Color.black);
			String str=String.valueOf(score);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("     SCORE: " +str,60,40);
			
			
			
					g.setColor(new Color(0xfff4d3));
					g.fillRect(300, 20, 200, 50);
					g.setColor(Color.black);
					String SC=String.valueOf(highScore);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					g.drawString("HIGH SCORE: " +SC,310,40);
		
	
	}

	
	
	//method drawing the grid
	public void drawGrid(Graphics2D g)
	
	{
		g.setColor(new Color(0xfff4d3));
		g.fillRect(550, 20, 200, 50);
		g.setColor(Color.black);
		String SC=String.valueOf(highScore);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Press esc to quit",610,40);

		
		Color gridColor = new Color(0xBBADA0);
		Color emptyColor = new Color(0xCDC1B4);
		if(start==false)
		{
		
		}
		else {
		g.setColor(gridColor);
        g.fillRoundRect(90, 90, 400, 400, 12, 12);
            for (int r = 1; r <= X; r++) {
                for (int c = 1; c <= Y; c++) {
                        g.setColor(emptyColor);
                        g.fillRoundRect(c * 100, r*100, 80, 80, 7, 7);
                    }
                }
		
		if(count!=0)
		{
			 for (int r = 1; r <= X; r++) {
	                for (int c = 1; c <= Y; c++) {
	                	t[r-1][c-1]=new tile(m[r-1][c-1]);            
	                        drawtile(g,r,c);
	                    }
	                }
		 
		}

		}}
		
		//method drawing tiles
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
	
	
	
	
	//method is UP key is pressed
	public void ArrowUp()
	{
	 int counter=1;
	 while (counter<X)
	 {
		 for (int y=0;y<Y;y++)
		 {
			 for(int x=X-1;x>0;x--)
			 {
				 if (m[x][y]==0)
				 {}
				 else
				 {
					 if (m[x-1][y]==0) 
					 {
						 t[x-1][y].value=t[x][y].value;
						 t[x][y].value=0;
						 
						 m[x-1][y]=t[x-1][y].value;
						 m[x][y]=0;
					 }
					 else
					 {
						 checkMoveable++;
					 }
					 
				 }
			 }
		 }
		 
		 counter++;
	 }}
	
	
	
	//method is DOWN key is pressed
	 public void ArrowDown()
		{
		 int counter=1;
		 while (counter<X)
		 {
			 for (int y=0;y<Y;y++)
			 {
				 for(int x=0;x<X-1;x++)
				 {
					 if (m[x][y]==0)
					 {}
					 else
					 {
						 if (m[x+1][y]==0) 
						 {
							 t[x+1][y].value=t[x][y].value;
							 t[x][y].value=0;
							 m[x+1][y]=t[x+1][y].value;
							 m[x][y]=0;
						 }
						 else
						 {
							checkMoveable++;
						 }
						 
					 }
				 }
			 }
			 
			 counter++;
		 }
		 
		 }
		 
	 
	 
	 
	//method is LEFT key is pressed
	 public void ArrowLeft()
		{
		 
		 int counter=1;
		 
		 while (counter<X)
		 {
			 for (int x=0;x<X;x++)
			 {
				 for(int y=Y-1;y>0;y--)
				 {
					 if (m[x][y]==0)
					 {}
					 else
					 {
						 if (m[x][y-1]==0) 
						 {
							 t[x][y-1].value=t[x][y].value;
							 t[x][y].value=0;
							 m[x][y-1]=t[x][y-1].value;
							 m[x][y]=0;
						 }
						 else
						 {
							 checkMoveable++;
}
						 
					 }
				 }
			 }
			 
			 counter++;
			 
		 }
		
		}
	 
	 
	 
	//method is RIGHT key is pressed
	 public void ArrowRight()
		{
		 
		 int counter=1;
		 
		 while (counter<X)
		 {
			 for (int x=0;x<X;x++)
			 {
				 for(int y=0;y<Y-1;y++)
				 {
					 if (m[x][y]==0)
					 {}
					 else
					 {
						 if (m[x][y+1]==0) 
						 {
							 t[x][y+1].value=t[x][y].value;
							 t[x][y].value=0;
							 m[x][y+1]= t[x][y+1].value;
							 m[x][y]=0;
						 }
						 else
						 {
							 checkMoveable++;
						 }
						 
					 }
				 }
			 }
			 
			 counter++;
			 
		 }
		 
		 
		}
	 
	 
	 
	 
	 //SUMING the values is UP key is pressed
	 public void SumUp()
		{
		
		 
		     for (int y=0;y<Y;y++)
			 {
				 for(int x=0;x<X-1;x++)
				 {
					 if (m[x][y]==m[x+1][y]) 
						 {
							 t[x][y].value=(2*(t[x][y].value));
							 score+=t[x][y].value;
							 t[x+1][y].value=0;
							 m[x][y]= t[x][y].value;
							 m[x+1][y]=0;
						 }
						 else
						 {
							 
						 }
						 
					 
				 }
				 
			 }
		     
		     ArrowUp();
			 
		   
			
		}
		
	 
	 
	 //SUMING the values is DOWN key is pressed
	 public void SumDown()
		{
		
		 
		     for (int y=0;y<Y;y++)
			 {
				 for(int x=X-1;x>0;x--)
				 {
					 if (m[x][y]==m[x-1][y]) 
						 {

						 t[x][y].value=(2*(t[x][y].value));
						 t[x-1][y].value=0;
						 score+=t[x][y].value;
						 m[x][y]= t[x][y].value;
						 m[x-1][y]=0;
						 }
						 else
						 {
							
						 }
						 
					 
				 }
				 
			 }
		     
		     ArrowDown();
		    
			 
			
		}
	 
	 
	 //SUMING the values is RIGHT key is pressed
	 public void SumRight()
		{
		 
		     for (int x=0;x<X;x++)
			 {
				 for(int y=Y-1;y>0;y--)
				 {
					 if (m[x][y]==m[x][y-1]) 
						 {

						 t[x][y].value=(2*(t[x][y].value));
						 t[x][y-1].value=0;
						 score+=t[x][y].value;

						 m[x][y]= t[x][y].value;
						 m[x][y-1]=0;
						 }
						 else
						 {
							
						 }
						 
					 
				 }
				 
			 }
		     
		     ArrowRight();
		    
			
		}
	 
	 
	 //SUMING the values is LEFT key is pressed
	 public void SumLeft()
		{
		
		     for (int x=0;x<X;x++)
			 {
				 for(int y=0;y<Y-1;y++)
				 {
					 if (m[x][y]==m[x][y+1]) 
						
					 {

						 t[x][y].value=(2*(t[x][y].value));
						 t[x][y+1].value=0;
						 score+=t[x][y].value;
						 m[x][y]= t[x][y].value;
						 m[x][y+1]=0;
					 }
						 else
						 {
							 
						 }
						 
					 
				 }
				 
			 }
		     
		     ArrowLeft();
			 
			 
			
		}


	
	 public static void main(String []args)
	{
	 	JFrame f = new JFrame();
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle("2048");
        f.setResizable(true);
        f.add(new GAME(), BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	
	}
	 
	@Override
	public void mouseClicked(MouseEvent e) {
		
		 for (Rectangle r: rectangles)
	{
			 if(r1.isSelected())
			 {
			   j=new JFrame();
			   j.setSize(700, 700);
  		       j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		       j.setTitle("2048");
  		       j.setResizable(true);
  		       j.add(new Game8by8(), BorderLayout.CENTER);  		       
  		       j.setLocationRelativeTo(null);
  		       j.setVisible(true);
			 }
			 
			 if(r2.isSelected())
			 {
				
			   j=new JFrame();
			   j.setSize(600, 600);
  		       j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		       j.setTitle("2048");
  		       j.setResizable(true);
  		       j.add(new Game4by4(), BorderLayout.CENTER);
  		       j.setLocationRelativeTo(null);
  		       j.setVisible(true);     
			 }
			 
			 
			 if(r3.isSelected())
			 {
				
			   j=new JFrame();
			   j.setSize(550, 550);
  		       j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		       j.setTitle("2048");
  		       j.setResizable(true);
  		       j.add(new Game6by6(), BorderLayout.CENTER);
  		       j.setLocationRelativeTo(null);
  		       j.setVisible(true);
			 }
			 
			 
			 if(r4.isSelected())
			 {
				
			   j=new JFrame();
			   j.setSize(500, 500);
  		       j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		       j.setTitle("2048");
  		       j.setResizable(true);
  		       j.add(new Game5by5(), BorderLayout.CENTER);
  		       j.setLocationRelativeTo(null);
  		       j.setVisible(true);  
			 }
		    
		    }
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}


