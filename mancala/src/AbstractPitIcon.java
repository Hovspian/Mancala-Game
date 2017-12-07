import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;


public abstract class AbstractPitIcon implements Icon
{
	private int stones;
	private int width;
	private int height;
	private Color pitColor;
	private Color stoneColor;

	public AbstractPitIcon(int stones, double width, double height, Color pitColor, Color stoneColor)
	{
		this.stones = stones;
		this.width = (int) width;
		this.height = (int) height;
		this.pitColor = pitColor;
		this.stoneColor = stoneColor;
	}


	public int getIconHeight()
	{
		return height;
	}

	public int getIconWidth()
	{
		return width;
	}

	public int getStones()
	{
		return stones;
	}

	public Color getPitColor()
	{
		return pitColor;
	}

	public Color getStoneColor()
	{
		return stoneColor;
	}


}
