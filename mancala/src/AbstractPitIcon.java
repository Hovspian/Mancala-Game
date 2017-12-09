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
	private int width;
	private int height;
	private Color pitColor;
	private Color stoneColor;

	/**
	 * Constructs an icon for a pit with the specified size, colors, and stones
	 * @param stones the number of stones in the pit
	 * @param width the width of the pit
	 * @param height the height of the pit
	 * @param pitColor the color of the pit
	 * @param stoneColor the color of the stones
	 */
	public AbstractPitIcon(int stones, double width, double height, Color pitColor, Color stoneColor)
	{
		this.stones = stones;
		this.width = (int) width;
		this.height = (int) height;
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
}
