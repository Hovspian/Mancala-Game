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


		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		draw();
	}


	public void draw()
	{
		this.removeAll();
		

		
		
		this.add(strategy.drawPits(game), BorderLayout.CENTER);
		this.add(strategy.drawMancala(game.checkPit1()[6]), BorderLayout.EAST);
		this.add(strategy.drawMancala(game.checkPit2()[6]), BorderLayout.WEST);
		

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		strategy.drawBackground(g2);
	}

	public void stateChanged(ChangeEvent e)
	{
//		if (game.checkEnd()) // If game's over do the final move and make a frame to show winner
//		{
			game.finalMove();
			
			
			JFrame frame = new JFrame();
			
			JTextArea winner = new JTextArea("Player " + game.winner() + " wins!");
			
			winner.setEditable(false);
			winner.setBackground(Color.WHITE);
			frame.add(winner);
			
			frame.setLocation(800, 500);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
//		}
		
		
		draw();
		revalidate();
		repaint();
	}

	public Mancala getGame()
	{
		return game;
	}
}
