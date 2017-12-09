import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * An abstract class that defines the strategy for the board and draws them using the template method pattern.
 * 
 * @author Hovsep Lalikian
 *
 */
public abstract class AbstractBoardStrategy implements BoardStrategy 
{
	private Color boardColor;
	private Color pitColor;
	private Color stoneColor;
	private Color textColor;
	private Shape boardShape;
	private Shape mancalaShape;
	private Shape pitShape;

	/**
	 * Constructs a BoardStrategy that will draw the specified shapes with the specified colors.
	 * @param boardColor the board's background color
	 * @param pitColor the color of the mancalas and pits
	 * @param boardShape the shape of the board
	 * @param mancalaShape the shape of the mancalas
	 * @param pitShape the shape of the pits
	 */
	public AbstractBoardStrategy(Color boardColor, Color pitColor, Shape boardShape, Shape mancalaShape, Shape pitShape)
	{
		this.boardColor = boardColor;
		this.pitColor = pitColor;
		this.stoneColor = Color.BLACK;
		this.textColor = Color.BLACK;

		this.boardShape = boardShape;
		this.mancalaShape = mancalaShape;
		this.pitShape = pitShape;
	}


	public JLabel drawMancala(int stones)
	{
		return new JLabel(new MancalaIcon(stones, mancalaShape, pitColor, stoneColor));
	}

	public JPanel drawPits(Mancala game)
	{
		JPanel fullPanel = new JPanel(new BorderLayout());
		fullPanel.setOpaque(false);
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);

		boolean turn = game.getTurn();
		int[] player1Pits = game.checkPit1();
		int[] player2Pits = game.checkPit2();

		// Draws player B's pits and their stones
		for (int i = 5; i >= 0; i--)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);

			JLabel pitNumber = new JLabel("             B" + (i + 1));
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.NORTH);

			PitIcon pit = new PitIcon(i, player2Pits[i], pitShape, pitColor, stoneColor);
			JLabel label = new JLabel(pit);

			// Makes player B's pits interactable if it's his turn
			if (!turn)
			{
				label.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						System.out.println("Clicked B");
						game.pitLogic(pit.getPosition());
					}
				});
			}
			pitPanel.add(label, BorderLayout.SOUTH);
			topPanel.add(pitPanel);
		}


		// Draws player A's pits and their stones
		for (int i = 0; i <= 5; i++)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);

			JLabel pitNumber = new JLabel("             A" + (i + 1));
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.SOUTH);

			PitIcon pit = new PitIcon(i, player1Pits[i], pitShape, pitColor, stoneColor);
			JLabel label = new JLabel(pit);

			// Makes player A's pits interactable if it's his turn
			if (turn)
			{
				label.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						System.out.println("Clicked A");
						game.pitLogic(pit.getPosition());
					}
				});
			}
			pitPanel.add(label, BorderLayout.NORTH);

			bottomPanel.add(pitPanel);
		}

		fullPanel.add(topPanel, BorderLayout.NORTH);
		fullPanel.add(bottomPanel, BorderLayout.SOUTH);
		return fullPanel;
	}

	public void drawBackground(Graphics2D g2)
	{
		g2.setColor(boardColor);
		g2.fill(boardShape);
		g2.setColor(Color.BLACK);
		g2.draw(boardShape);
	}
}
