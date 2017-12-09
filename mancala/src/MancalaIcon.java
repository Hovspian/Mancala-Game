import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * An icon representing a single mancala.
 * 
 * @author Hovsep Lalikian
 *
 */
public class MancalaIcon extends AbstractPitIcon
{
	private Shape shape;

	/**
	 * Constructs an icon for this mancala.
	 * @param stones the number of stones in the mancala
	 * @param shape the shape of the mancala
	 * @param mancalaColor the color of the mancala
	 * @param stoneColor the color of the stones
	 */
	public MancalaIcon(int stones, Shape shape, Color mancalaColor, Color stoneColor)
	{
		super(stones, shape.getBounds().getWidth() + 1, shape.getBounds().getHeight(), mancalaColor, stoneColor);
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
	 * Draws the stones for the mancala.
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
