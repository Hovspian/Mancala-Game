import java.awt.Dimension;
import java.awt.geom.Path2D;

/**
 * A shape representing a diamond.
 * 
 * @author Hovsep Lalikian
 *
 */
public class DiamondShape extends Path2D.Double
{
	/**
	 * Constructs a diamond shape with the given dimensions.
	 * @param d the dimension of the diamond
	 */
	public DiamondShape(Dimension d)
	{
		moveTo(d.getWidth() / 2, 0);
		lineTo(d.getWidth(), d.getHeight() / 2);
		lineTo(d.getWidth() / 2, d.getHeight());
		lineTo(0, d.getHeight() / 2);
		lineTo(d.getWidth() / 2, 0);
	}
}
