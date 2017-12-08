import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;



public class Board extends JPanel implements ChangeListener
{
	private BoardStrategy strategy;
	private Mancala game;

	public Board(Mancala game, BoardStrategy strategy)
	{
		super(new BorderLayout());
		this.setPreferredSize(new Dimension((int)BoardStrategy.BOARD_SIZE.getSize().getWidth() + 25, (int)BoardStrategy.BOARD_SIZE.getSize().getHeight() + 25));

		this.game = game;
		this.strategy = strategy;
		this.strategy.setParent(this);


		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		draw();
	}


	public void draw()
	{
		this.removeAll();
		this.add(strategy.drawPits(), BorderLayout.CENTER);
		this.add(strategy.drawMancala("a"), BorderLayout.EAST);
		this.add(strategy.drawMancala("b"), BorderLayout.WEST);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		strategy.drawBackground(g2);
	}

	public void stateChanged(ChangeEvent e)
	{
		draw();
		revalidate();
		repaint();
	}

	public Mancala getGame()
	{
		return game;
	}
}
