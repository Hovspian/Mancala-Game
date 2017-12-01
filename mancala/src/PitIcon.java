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
		g2.fill(new Ellipse2D.Double(getIconWidth() / 2 - 5, getIconHeight() / 2 - 5, 10, 10));
	}

	public int getPosition()
	{
		return position;
	}
}
