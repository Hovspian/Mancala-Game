import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


public class Mancala extends AbstractPit
{
	private Shape shape;
	
	public Mancala(Shape shape, Color mancalaColor, Color stoneColor)
	{
		super(shape.getBounds().getWidth() + 1, shape.getBounds().getHeight(), mancalaColor, stoneColor);
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
