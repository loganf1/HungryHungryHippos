/*
 * Hungry Hungry Hippos Game recreated in java
 * Collaborative project: Logan Fingerhuth & Jonathan Nam
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class HungryHungryHippos implements KeyListener
{
	private final Dimension PANEL_SIZE = new Dimension(700, 700);
	JLabel Title, Land, Hippo;
	JPanel jp;
	public playingArea PA;
	public TitleScreen TS;
	boolean Begin = false;
	JFrame frame;
	
	public static void main(String [] args)
	{
		HungryHungryHippos HHH = new HungryHungryHippos();
	}
	
	public HungryHungryHippos()
	{
		PA = new playingArea();
		TS =  new TitleScreen();
		jp = new JPanel (new CardLayout());
		jp.setSize(PANEL_SIZE);
		jp.add(TS, "title");
		jp.add(PA, "game");
		frame = new JFrame ();
		frame.setFocusable(true);
		frame.requestFocus();
		frame.addKeyListener(this);
		frame.add(jp);
		frame.setVisible(true);
		frame.setSize(PANEL_SIZE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void keyPressed(KeyEvent k) 
	{
		if (k.getKeyCode() == KeyEvent.VK_ENTER)
		{
			CardLayout CL = (CardLayout) jp.getLayout();
			CL.show(jp, "game");
			PA.setFocusable(true);
			PA.requestFocus();
		}
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}

	public class playingArea extends JPanel implements Runnable, MouseListener, KeyListener
	{
		int rectX, rectY, deltaX, deltaY, radX, radY;
		Thread t;
		JLabel label;
		AffineTransform identity = new AffineTransform(1.0,0.0,0.0,1.0,0.0,0.0);
		boolean left = false, right = false, up = false, down = false;
		ArrayList <Ball> balls = new ArrayList<>();
		int cnt = 0, numBalls = 0;
		boolean first = true;
		private Image BlueHippo, BlueHippoExt, PinkHippo, PinkHippoExt, GreenHippo, GreenHippoExt, OrangeHippo, OrangeHippoExt, background;
		hippoTimer l, r, u, d;
		private ArrayList <Ball> blueCap = new ArrayList<>();
		private ArrayList <Ball> greenCap = new ArrayList<>();
		private ArrayList <Ball> pinkCap = new ArrayList<>();
		private ArrayList <Ball> orangeCap = new ArrayList<>();
		
		public playingArea() 
		{
			setBalls();
			LaunchBalls();
		    try 
		    {
		    	BlueHippo = ImageIO.read(new File("src\\BH_Cut.png"));
		    	BlueHippoExt = ImageIO.read(new File("src\\BHE_Cut.png"));
		    	PinkHippo = ImageIO.read(new File("src\\PH_Cut.png"));
		    	PinkHippoExt = ImageIO.read(new File("src\\PHE_Cut.png"));
		    	GreenHippo = ImageIO.read(new File("src\\GH_Cut.png"));
		    	GreenHippoExt = ImageIO.read(new File("src\\GHE_Cut.png"));
		    	OrangeHippo = ImageIO.read(new File("src\\OH_Cut.png"));
		    	OrangeHippoExt = ImageIO.read(new File("src\\OHE_Cut.png"));
		    	background = ImageIO.read(new File ("src\\background.png"));
		    } 
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    l = new hippoTimer(0); r = new hippoTimer(0);
		    d = new hippoTimer(0); u = new hippoTimer(0);
			radX = 20;
			radY = 20;
			
			t = new Thread(this);
			t.start();
			addMouseListener(this);
			//label = new JLabel("Clicks: ");
			//label.setPreferredSize(new Dimension (100,50));
			setLayout(null);
			//label.setBounds(new Rectangle(new Point(250, 250), label.getPreferredSize()));
			//add(label);
			setBackground(Color.WHITE);
	
			setFocusable(true);
			requestFocus();
			addKeyListener(this);	
		}
		//Allows User to set Ball amount
		public void setBalls() 
		{
			String in = (String) JOptionPane.showInputDialog("Enter Number of Balls (1-10): ");
			numBalls = Integer.parseInt(in);
			//numBalls = 60;
		}
		//Sets initial Ball Speed / Direction
		public void LaunchBalls()
		{
			for(int i = 0; i < numBalls; i ++)
			{
				balls.add(new Ball(350, 350, (((int) (Math.random() * 359)) + 1), (((int) (Math.random() * 5)) + 2)));
			}
		}
		//Paints Game
		public void paintComponent(Graphics g) 
		{
		    super.paintComponent(g);
		    g.drawImage(background, 0, 0,
		              (int)getBounds().getWidth(), (int)getBounds().getHeight(), this);
			Graphics2D g2d = (Graphics2D)g;
			AffineTransform trans = new AffineTransform(identity);
		
			if (left)
			{
				trans.setTransform(identity);
				trans.translate(280, 295);
				trans.rotate(Math.toRadians(90));
				g2d.drawImage(BlueHippoExt, trans, this);
			}
			else
			{
				trans.setTransform(identity);
				trans.translate(209, 299);
				trans.rotate(Math.toRadians(90));
				g2d.drawImage(BlueHippo, trans, this);
			}
			if (right)
			{
				trans.setTransform(identity);
				trans.translate(437, 409);
				trans.rotate(Math.toRadians(270));
				g2d.drawImage(OrangeHippoExt, trans, this);
			}
			else
			{
				trans.setTransform(identity);
				trans.translate(510, 410);
				trans.rotate(Math.toRadians(270));
				g2d.drawImage(OrangeHippo, trans, this);
			}
			
			if (up)
			{
				trans.setTransform(identity);
				trans.translate(410, 256);
				trans.rotate(Math.toRadians(180));
				g2d.drawImage(PinkHippoExt, trans, this);
			}	
			else
			{
				trans.setTransform(identity);
				trans.translate(413, 180);
				trans.rotate(Math.toRadians(180));
				g2d.drawImage(PinkHippo, trans, this);
			}
			
			if (down)
				g.drawImage(GreenHippoExt, 294, 424, this);
			else
				g.drawImage(GreenHippo, 295, 500, this);
			int greenX = 413, greenY = 600;
			for (int i = 0; i < greenCap.size(); i ++)
			{
				g.setColor(Color.BLACK);
				g.fillOval(greenX, greenY, radX, radY);
				if (i == 5)
				{
					greenX = 390;
					greenY = 620;
				}
				if (i == 11)
				{
					greenX = 386;
					greenY = 640;
				}
				greenX += 20;
			}
			
			int blueX = 80, blueY = 416;
			for (int i = 0; i < blueCap.size(); i ++)
			{
				g.setColor(Color.BLACK);
				g.fillOval(blueX, blueY, radX, radY);
				if (i == 5)
				{
					blueX = 60;
					blueY = 393;
				}
				if (i == 11)
				{
					blueX = 40;
					blueY = 387;
				}
				blueY += 20;
			}
			
			int pinkX = 275, pinkY = 20;
			for (int i = 0; i < pinkCap.size(); i ++)
			{
				g.setColor(Color.BLACK);
				g.fillOval(pinkX, pinkY, radX, radY);
				if (i == 5)
				{
					pinkX = 287;
					pinkY = 40;
				}
				if (i == 11)
				{
					pinkX = 287;
					pinkY = 60;
				}
				pinkX -= 20;
			}			
			
			int orX = 610, orY = 268;
			for (int i = 0; i < orangeCap.size(); i ++)
			{
				g.setColor(Color.BLACK);
				g.fillOval(orX, orY, radX, radY);
				if (i == 5)
				{
					orX = 630;
					orY = 292;
				}
				if (i == 11)
				{
					orX = 650;
					orY = 298;
				}
				orY -= 20;
			}
			
			for (Ball b: balls)
			{
				g.setColor(Color.BLACK);
				g.fillOval(b.getXpos(), b.getYpos(), radX, radY);
			}
			for (Ball b: balls)
			{
				b.update();
			}
		}
		//Updates Game
		public void run() 
		{
			try 
			{
				while(true) 
				{
					repaint();
					checkBalls();
					checkBounds();
					checkTimers();
					t.sleep(100);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Retracts Hippos if Time is up
		private void checkTimers()
		{
			if (l.isExpired())
				left = false;
			if (r.isExpired())
				right = false;
			if (u.isExpired())
				up = false;
			if (d.isExpired())
				down = false;
		}
		//Checks if Balls should be Eaten
		private void checkBalls()
		{
			for (int i = 0; i < balls.size(); i ++)
			{
				Ball b = balls.get(i);
				// pink
				if (up && b.getXpos() > 330 && b.getXpos() < 370 && b.getYpos() > 190 && b.getYpos() < 230)
				{
					pinkCap.add(balls.remove(i));
					i--;
				}
				// blue
				if (left && b.getXpos() > 190 && b.getXpos() < 240 && b.getYpos() > 330 && b.getYpos() < 370)
				{
					blueCap.add(balls.remove(i));
					i--;
				}
				// green
				if (down && b.getXpos() > 330 && b.getXpos() < 370 && b.getYpos() > 270 && b.getYpos() < 310)
				{
					greenCap.add(balls.remove(i));
					i--;
				}
				// orange
				if (right && b.getXpos() > 460 && b.getXpos() < 510 && b.getYpos() > 330 && b.getYpos() < 370)
				{
					orangeCap.add(balls.remove(i));
					i--;
				}
			}
		}
		
		private void checkBounds() 
		{
			for (Ball b: balls)
			{
				if (b.getR() > 260)
				{
					b.bounce();
					b.update(); b.update();b.update();
				}
				if (b.getR() > 275)
				{
					b.set259(); 
				}
			}
		}
	
		public void update(Graphics g) 
		{
			paintComponent(g);
		} 
	
		public void mousePressed(MouseEvent e) 
		{
			for (Ball b: balls)
			{
				if ((Math.abs(e.getX() - b.getXpos() - radX/2) <= 15) && (Math.abs(e.getY() - b.getYpos() - radY/2) <= 15))
					cnt++;
				label.setText("Clicks: " + cnt);
			}
		}
	
		public void keyPressed(KeyEvent k) 
		{
		    if (k.getKeyCode() == KeyEvent.VK_L) 
			{
				//System.out.println("Left");
				left = true;
				l = new hippoTimer(500);
				//repaint();
			}
		    if (k.getKeyCode() == KeyEvent.VK_R) 
		    {
		    	//System.out.println("Right");
		    	right = true;
		    	r = new hippoTimer(500);
		    	//repaint();
		    }
		    if (k.getKeyCode() == KeyEvent.VK_U) 
		 	{
		    	//	System.out.println("Up");
		    	up = true;
		    	u = new hippoTimer(500);
		    	//repaint();
		 	}
		    if (k.getKeyCode() == KeyEvent.VK_D) 
		    {
		    	//System.out.println("Down");
		    	down = true;
		    	d = new hippoTimer(500);
		    	//repaint();
		    }
		}
		
		public void mouseEntered (MouseEvent arg0) {}
		public void mouseExited (MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent k) {}
	}
}