import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
 
public class BlinkLabel extends JLabel 
{
  private static final long serialVersionUID = 1L;
  private static final int BLINKING_RATE = 400; // in ms
  private boolean blinkingOn = true;
  
  public BlinkLabel(String text) 
  {
    super(text);
    Timer timer = new Timer(BLINKING_RATE , new TimerListener(this));
    timer.setInitialDelay(0);
    timer.start();
  }
  
  public void setBlinking(boolean flag) 
  {
    this.blinkingOn = flag;
  }
  
  public void setFont(Font f)
  {
	  super.setFont(f);
  }
  
  public boolean getBlinking(boolean flag)
  {
    return this.blinkingOn;
  }

  private class TimerListener implements ActionListener 
  {
    private BlinkLabel bl;
    private Color bg, fg;
    private ArrayList <Color> colors = new ArrayList <>();
    private int colorIt;
   
    private boolean isForeground = true;
    
    public TimerListener(BlinkLabel bl) 
    {
      this.bl = bl;
      colors.add(Color.red.darker());colors.add(Color.red);colors.add(Color.orange.brighter());colors.add(Color.orange);
      colors.add(Color.yellow);colors.add(Color.green);colors.add(Color.green.brighter());colors.add(Color.blue);
      colors.add(Color.blue.brighter());colors.add(Color.cyan);colors.add(Color.magenta.brighter());
      colors.add(Color.magenta);colors.add(Color.magenta.darker());colors.add(Color.white.darker());colors.add(Color.white);
      fg = bl.getForeground();
      bg = bl.getBackground();
      colorIt = 0;
    }
 
    public void actionPerformed(ActionEvent e) 
    {
      if (bl.blinkingOn) 
      {
        if (isForeground)
        {
          bl.setForeground(colors.get(colorIt));
          colorIt ++;
          if (colorIt >= colors.size()) colorIt = 0;
        }
      }
      else {
        // here we want to make sure that the label is visible
        // if the blinking is off.
        if (isForeground) {
          bl.setForeground(fg);
          isForeground = false;
        }
      }
    }
    
  }
 
  // --- for testing 
  private static void createAndShowUI() {
    JFrame frame = new JFrame("BlinkLabel");
    final BlinkLabel bl = new BlinkLabel("I'm blinking!");
    
    frame.getContentPane().setLayout(new java.awt.FlowLayout());
    frame.getContentPane().add(bl);
    
    JButton b = new JButton("toogle blink");
    b.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            bl.blinkingOn = !bl.blinkingOn;
          }
        });
    frame.getContentPane().add(b);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }
 
  public static void main(String[] args)  {
    java.awt.EventQueue.invokeLater(new Runnable(){
      public void run(){
        createAndShowUI();
      }
    });
  }
  // ---
}