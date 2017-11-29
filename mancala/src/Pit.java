import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class Pit extends AbstractPit
{
	private Shape shape;
	
	public Pit(Shape shape, Color pitColor, Color stoneColor)
	{
		super(shape.getBounds().getWidth(), shape.getBounds().getWidth(), pitColor, stoneColor);
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
}
