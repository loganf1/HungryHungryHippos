import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JPanel implements Runnable, MouseListener, KeyListener
{
	private final Dimension PANEL_SIZE = new Dimension(700, 700);
	private Image titleImage, landImage, hippoImage;
	JLabel Title, Land, Hippo;
	Thread t;
	BlinkLabel Start;
	boolean Begin = false;
	//
	public TitleScreen()
	{
		t = new Thread(this);
		t.start();
		try 
		{
			titleImage = ImageIO.read(new File("src\\HHHTitle.png"));
			landImage = ImageIO.read(new File("src\\CoverHippoGround.png"));
			hippoImage = ImageIO.read(new File("src\\Hippo8.png"));
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		Title = new JLabel(new ImageIcon(titleImage));
		Land = new JLabel(new ImageIcon(landImage));
		hippoImage = hippoImage.getScaledInstance(500, 400, Image.SCALE_DEFAULT);
		Hippo = new JLabel(new ImageIcon(hippoImage));
		setSize(PANEL_SIZE);
		Title.setPreferredSize(new Dimension (700,700));
		Title.setBounds(new Rectangle(new Point(0, -220), Title.getPreferredSize()));
		Land.setPreferredSize(new Dimension (700,700));
		Land.setBounds(new Rectangle(new Point(0, 150), Land.getPreferredSize()));
		Hippo.setPreferredSize(new Dimension (500,400));
		Hippo.setBounds(new Rectangle(new Point(150, 290), Hippo.getPreferredSize()));
		Start = new BlinkLabel ("Press Enter to Start");
		Start.setFont(new Font ("Arial",Font.BOLD, 48));
		Start.setVisible(true);
		Start.setPreferredSize(new Dimension (700,700));
		Start.setBounds(new Rectangle(new Point(150, -50), Start.getPreferredSize()));
		add(Start);
		add(Title);
		add(Land);
		add(Hippo);
		setLayout(null);
		Start.addMouseListener(this);
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		//Start.setForeground(Color.)
		//add(label);
		//label.setBorder(new BevelBorder(0, Color.black,Color.cyan));
	}

	public void keyPressed(KeyEvent k) 
	{
		System.out.println("Enter");
	}

	public void mousePressed(MouseEvent e) 
	{
		System.out.println("Pressed");
	}

	public void run() 
	{
		try 
		{
			while(true) 
			{
				repaint();
				t.sleep(100);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Graphics g) 
	{
		paint(g);
	} 

	public void paint(Graphics g)
	{
		super.paint(g);
	}

	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}