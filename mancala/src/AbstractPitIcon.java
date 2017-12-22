import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * An abstract class that creates an icon in the shape of a pit. 
 * A pit can be either a mancala or an interactable pit.
 * This class will handle the general information about the pit while its subclasses handle the drawing of the icon.
 * 
 * @author Hovsep Lalikian
 *
 */
public abstract class AbstractPitIcon implements Icon
{
	private int stones;
	private Shape shape;
	private int width;
	private int height;
	private Color pitColor;
	private Color stoneColor;

	/**
	 * Constructs an icon for a pit with the specified size, colors, and stones
	 * @param stones the number of stones in the pit
	 * @param shape the Shape of the pit
	 * @param pitColor the color of the pit
	 * @param stoneColor the color of the stones
	 */
	public AbstractPitIcon(int stones, Shape shape, Color pitColor, Color stoneColor)
	{
		this.stones = stones;
		this.shape = shape;
		this.width = (int) (shape.getBounds().getWidth() + 1);
		this.height = (int) shape.getBounds().getHeight();
		this.pitColor = pitColor;
		this.stoneColor = stoneColor;
	}


	@Override
	public int getIconHeight()
	{
		return height;
	}

	@Override
	public int getIconWidth()
	{
		return width;
	}

	/**
	 * Returns the number of stones in this pit
	 * @return the number of stones in this pit
	 */
	public int getStones()
	{
		return stones;
	}

	/**
	 * Returns the color of the pit
	 * @return the color of the pit
	 */
	public Color getPitColor()
	{
		return pitColor;
	}

	/**
	 * Returns the color of the stones
	 * @return the color of the stones
	 */
	public Color getStoneColor()
	{
		return stoneColor;
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) 
	{
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(getPitColor());
		g2.fill(shape);
		g2.setColor(Color.BLACK);
		g2.draw(shape);

		g2.setColor(getStoneColor());
		drawStones(g2);
	}

	/**
	 * Draws the stones for the pit.
	 * @param g2 the Graphics2D object being used to draw the stones
	 */
	private void drawStones(Graphics2D g2)
	{
		final int STONE_SIZE = BoardStrategy.STONE_SIZE;

		int stonesDrawn = 0;
		int xPos = getIconWidth() / 6;
		int yPos = getIconHeight() / 4;
		while (stonesDrawn < getStones())
		{
			xPos = getIconWidth() / 10 + stonesDrawn % 7 * STONE_SIZE / 2 + STONE_SIZE;
			yPos = getIconHeight() / 4 + stonesDrawn / 7 * STONE_SIZE / 2 + STONE_SIZE;

			g2.fill(new Ellipse2D.Double(xPos + ((stonesDrawn % 7) * (STONE_SIZE / 2)), yPos + stonesDrawn / 7 * STONE_SIZE / 2, STONE_SIZE, STONE_SIZE));			

			stonesDrawn++;
		}
	}
}
