import java.awt.*;
import java.awt.geom.*;

public class RigidBoard extends AbstractBoard
{
	public RigidBoard() 
	{
		super(Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE, new Rectangle2D.Double(10, 10, BOARD_SIZE.getWidth() + 5, BOARD_SIZE.getHeight() + 5), new Rectangle2D.Double(0, 5, MANCALA_SIZE.getWidth(), MANCALA_SIZE.getHeight() + 5), new DiamondShape(new Dimension(PIT_SIZE)));
	}
}
