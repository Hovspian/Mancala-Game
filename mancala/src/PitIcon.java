import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * An icon representing a single pit.
 * 
 * @author Hovsep Lalikian
 *
 */
public class PitIcon extends AbstractPitIcon
{
	private Shape shape;
	private int index;

	/**
	 * Constructs an icon for this pit.
	 * @param index the pit number
	 * @param stones the number of stones in the pit
	 * @param shape the shape of the pit
	 * @param pitColor the color of the pit
	 * @param stoneColor the color of the stones
	 */
	public PitIcon(int index, int stones, Shape shape, Color pitColor, Color stoneColor)
	{
		super(stones, shape.getBounds().getWidth(), shape.getBounds().getWidth(), pitColor, stoneColor);
		this.index = index;
		this.shape = shape;
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
	 * Returns the pit's index.
	 * @return the pit's index
	 */
	public int getIndex()
	{
		return index;
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
