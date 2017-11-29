import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.*;


public interface BoardStrategy 
{
	
	void drawAll();
	JLabel drawMancala();
	JPanel drawPits();
	void setParent(Board b);
	void drawBackground(Graphics2D g2);
	
	Dimension BOARD_SIZE = new Dimension(900, 300);
	Dimension MANCALA_SIZE = new Dimension(90, 270);
	Dimension PIT_SIZE = new Dimension(90, 90);
}
