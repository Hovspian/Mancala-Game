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
	/**
	 * Constructs an icon for this mancala.
	 * @param stones the number of stones in the mancala
	 * @param shape the shape of the mancala
	 * @param mancalaColor the color of the mancala
	 * @param stoneColor the color of the stones
	 */
	public MancalaIcon(int stones, Shape shape, Color mancalaColor, Color stoneColor)
	{
		super(stones, shape, mancalaColor, stoneColor);
	}
}
