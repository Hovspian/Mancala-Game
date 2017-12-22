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
		super(stones, shape, pitColor, stoneColor);
		this.index = index;
	}

	/**
	 * Returns the pit's index.
	 * @return the pit's index
	 */
	public int getIndex()
	{
		return index;
	}
}
