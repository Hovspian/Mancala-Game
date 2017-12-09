import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class PitIcon extends AbstractPitIcon
{
	private Shape shape;
	private int position;


	public PitIcon(int position, int stones, Shape shape, Color pitColor, Color stoneColor)
	{
		super(stones, shape.getBounds().getWidth(), shape.getBounds().getWidth(), pitColor, stoneColor);
		this.position = position;
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

	public int getPosition()
	{
		return position;
	}
	
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
