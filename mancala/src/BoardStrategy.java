import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.*;


/**
 * An interface using the strategy pattern to draw parts of a board.
 * 
 * @author Hovsep Lalikian
 */
public interface BoardStrategy 
{
	/**
	 * Returns a JLabel that draws a mancala with the specified number of stones.
	 * @param stones the number of stones in the mancala
	 * @return a JLabel with an icon of a mancala
	 */
	JLabel drawMancala(int stones);
	/**
	 * Draws pits and their stones onto a JPanel and returns the JPanel. 
	 * The pits are only be clickable if it is that player's turn.
	 * @param game the game for the pits being drawn
	 * @return a JPanel containing all pits and their stones as JLabels
	 */
	JPanel drawPits(Mancala game);
	/**
	 * Draws the background of the board.
	 * @param g2 the Graphics2D object being used to draw the board
	 */
	void drawBackground(Graphics2D g2);
	
	Dimension BOARD_SIZE = new Dimension(900, 300);
	Dimension MANCALA_SIZE = new Dimension(90, 270);
	Dimension PIT_SIZE = new Dimension(90, 90);
	int STONE_SIZE = 8;
}
