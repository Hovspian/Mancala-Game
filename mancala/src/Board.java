import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;


/**
 * A board that acts as the view for the mancala game. 
 * 
 * @author Hovsep Lalikian
 *
 */
public class Board extends JPanel implements ChangeListener
{
	private BoardStrategy strategy;
	private Mancala game;

	/**
	 * Constructs a board for the specified game with the given strategy for the board layout.
	 * @param game the game being shown by the board
	 * @param strategy the strategy being used for the board layout.
	 */
	public Board(Mancala game, BoardStrategy strategy)
	{
		super(new BorderLayout());
		this.setPreferredSize(new Dimension((int)BoardStrategy.BOARD_SIZE.getSize().getWidth() + 25, (int)BoardStrategy.BOARD_SIZE.getSize().getHeight() + 25));

		this.game = game;
		this.strategy = strategy;


		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		draw();
	}


	/**
	 * Draws the pits and mancalas for the board.
	 */
	private void draw()
	{
		this.removeAll();

		this.add(strategy.drawPits(game), BorderLayout.CENTER);
		this.add(strategy.drawMancala(game.checkPit1()[6]), BorderLayout.EAST);
		this.add(strategy.drawMancala(game.checkPit2()[6]), BorderLayout.WEST);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		strategy.drawBackground(g2);
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		if (game.checkEnd()) // If game's over do the final move and make a frame to show winner
		{
			game.finalMove();

			JFrame frame = new JFrame();

			JTextArea winner = new JTextArea("Player " + game.winner() + " wins!");
			winner.setEditable(false);
			winner.setBackground(Color.WHITE);
			
			Font f = winner.getFont();
			f = new Font(f.getFontName(), f.getStyle(), 30);
			winner.setFont(f);
			
			frame.add(winner);

			frame.setLocation(800, 500);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}

		draw();
		revalidate();
		repaint();
	}
}