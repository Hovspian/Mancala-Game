import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;


public abstract class AbstractBoard implements BoardStrategy 
{
	private Board parent;
	private Color boardColor;
	private Color pitColor;
	private Color stoneColor;
	private Color textColor;
	private Shape boardShape;
	private Shape mancalaShape;
	private Shape pitShape;
	private Mancala game;

	public AbstractBoard(Color boardColor, Color pitColor, Color stoneColor, Color textColor, Shape boardShape, Shape mancalaShape, Shape pitShape)
	{
		this.boardColor = boardColor;
		this.pitColor = pitColor;
		this.stoneColor = stoneColor;
		this.textColor = textColor;

		this.boardShape = boardShape;
		this.mancalaShape = mancalaShape;
		this.pitShape = pitShape;
	}


	public void setParent(Board b)
	{
		this.parent = b;
		game = parent.getGame();
	}


	@Override
	public void drawAll() 
	{
		drawPits();
	}

	public JLabel drawMancala(String player)
	{
		int stones;
		if (player.equals("a"))
		{
			stones = parent.getGame().checkPit1()[6];
		}
		else if (player.equals("b"))
		{
			stones = parent.getGame().checkPit2()[6];
		}
		else
		{
			return null;
		}
		return new JLabel(new MancalaIcon(stones, mancalaShape, pitColor, stoneColor));
	}

	public JPanel drawPits()
	{
		Mancala game = parent.getGame();

		JPanel fullPanel = new JPanel(new BorderLayout());
		fullPanel.setOpaque(false);
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);

		boolean turn = game.getTurn();
		int[] player1Pits = game.checkPit1();
		int[] player2Pits = game.checkPit2();

		for (int i = 5; i >= 0; i--)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);

			JLabel pitNumber = new JLabel("             B" + (i + 1));
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.NORTH);

			PitIcon pit = new PitIcon(i, player2Pits[i], pitShape, pitColor, stoneColor);
			JLabel label = new JLabel(pit);

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


		for (int i = 0; i <= 5; i++)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);

			JLabel pitNumber = new JLabel("             A" + (i + 1));
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.SOUTH);

			PitIcon pit = new PitIcon(i, player1Pits[i], pitShape, pitColor, stoneColor);
			JLabel label = new JLabel(pit);

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

	protected JPanel getParent()
	{
		return parent;
	}

}
