import java.awt.*;
import java.awt.geom.*;

public class SimpleBoard extends AbstractBoard
{
	public SimpleBoard() 
	{
		super(Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, new RoundRectangle2D.Double(10, 10, BOARD_SIZE.getWidth() + 5, BOARD_SIZE.getHeight() + 5, 40, 40), new RoundRectangle2D.Double(0, 5, MANCALA_SIZE.getWidth(), MANCALA_SIZE.getHeight() + 5, 40, 40), new Ellipse2D.Double(0, 0, PIT_SIZE.getWidth(), PIT_SIZE.getHeight()));
	}
	
	public SimpleBoard(Color c1, Color c2)
	{
		super(c1, c2, Color.BLACK, Color.BLACK, new RoundRectangle2D.Double(10, 10, BOARD_SIZE.getWidth() + 5, BOARD_SIZE.getHeight() + 5, 40, 40), new RoundRectangle2D.Double(0, 5, MANCALA_SIZE.getWidth(), MANCALA_SIZE.getHeight() + 5, 40, 40), new Ellipse2D.Double(0, 0, PIT_SIZE.getWidth(), PIT_SIZE.getHeight()));
		
	}
}
