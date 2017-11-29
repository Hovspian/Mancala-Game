import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;


public abstract class AbstractPit implements Icon
{
	private int stones;
	private int width;
	private int height;
	private Color pitColor;
	private Color stoneColor;
	
	public AbstractPit(double width, double height, Color pitColor, Color stoneColor)
	{
		this.width = (int) width;
		this.height = (int) height;
		this.pitColor = pitColor;
		this.stoneColor = stoneColor;
		stones = 0;
	}
	
	
	public int getIconHeight()
	{
		return height;
	}
	
	public int getIconWidth()
	{
		return width;
	}
	
	protected void setStones(int amount)
	{
		stones = amount;
	}
	
	protected void addOne()
	{
		stones++;
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
