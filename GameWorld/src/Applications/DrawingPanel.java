package Applications;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

 public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, getWidth(), getHeight());

    //render.renderGame(g2, getWidth(), getHeight(), game, wallaway);
  }
} 
