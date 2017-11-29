import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;


public abstract class AbstractBoard implements BoardStrategy 
{
	private JPanel parent;
	private Color boardColor;
	private Color pitColor;
	private Color stoneColor;
	private Color textColor;
	private Shape boardShape;
	private Shape mancalaShape;
	private Shape pitShape;
	
//	protected static final int BOARD_SIZE = 
	
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
	public AbstractBoard()
	{
		
	}

	
	public void setParent(Board b)
	{
		this.parent = b;
	}
	
	
	@Override
	public void drawAll() 
	{
		drawPits();
	}
	
	public JLabel drawMancala()
	{
		return new JLabel(new Mancala(mancalaShape, pitColor, stoneColor));
	}
	
	public JPanel drawPits()
	{
		JPanel fullPanel = new JPanel(new BorderLayout());
		fullPanel.setOpaque(false);
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		for (int i = 6; i > 0; i--)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);
			
			JLabel pitNumber = new JLabel("             B" + i);
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.NORTH);
			
			JLabel pit = new JLabel(new Pit(pitShape, pitColor, stoneColor));
			pit.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					System.out.println("Clicked B");
					//game.moveMancala(i + 7);
				}
			});
			pitPanel.add(pit, BorderLayout.SOUTH);
			topPanel.add(pitPanel);
		}
		
		
		for (int i = 1; i <= 6; i++)
		{
			JPanel pitPanel = new JPanel(new BorderLayout());
			pitPanel.setOpaque(false);
			
			JLabel pitNumber = new JLabel("             A" + i);
			pitNumber.setForeground(textColor);
			pitPanel.add(pitNumber, BorderLayout.SOUTH);

			JLabel pit = new JLabel(new Pit(pitShape, pitColor, stoneColor));
			pit.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					System.out.println("Clicked A");
					//game.moveMancala(i - 1);
				}
			});
			pitPanel.add(pit, BorderLayout.NORTH);
			
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
