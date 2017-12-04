import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class BoardOptions 
{

	/**
	 * Using Andy's code of the game, using this constructor will create a frame asking for the layout of the board, as well as the color scheme 
	 * and number of stones for the game. Once each is accepted, a frame appears that shows the mancala board.
	 * 
	 */
	
	public BoardOptions(Game m)
	{
		// First frame, asking for the board layout and amount of stones
		JFrame frame = new JFrame();
		JTextArea header = new JTextArea("Choose a board layout:");
		header.setEditable(false);
		
		// This panel will create the header and box for layout.
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		boardPanel.add(header, BorderLayout.NORTH);
		JComboBox box = new JComboBox();
		box.addItem("Simple");
		box.addItem("Other");
		boardPanel.add(box, BorderLayout.CENTER);
		JPanel pitPanel = new JPanel();
		JButton accept = new JButton("Accept");
		
		JTextArea header2 = new JTextArea("Choose the max amount of stones: ");
		JComboBox pitNum = new JComboBox();
		pitNum.addItem("3");
		pitNum.addItem("4");
		pitPanel.add(header2, BorderLayout.NORTH);
		pitPanel.add(pitNum, BorderLayout.CENTER);
		
		// Combo box to choose the amount of pit stones for the game.
		
		
		frame.add(boardPanel, BorderLayout.NORTH);
		frame.add(pitPanel, BorderLayout.CENTER);
		accept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0) 
			{
				String stones = (String) pitNum.getSelectedItem(); // Gets the item chosen from the Combo Box
				String format = (String) box.getSelectedItem();
				if (format.equals("Simple"))
				{
					frame.dispose();
					pickColorScheme("Simple"); // Frame disappears and a new appears asking for color choice
				
				}
				else if (format.equals("Other"))
				{
					frame.dispose();
					pickColorScheme("Other");

				}
				
			}			
		});
		
		frame.add(accept, BorderLayout.SOUTH); // Accept button that will transitions into different frames.
		//
			frame.setLocation(800, 500); // Changes the location of the dialog box to start the game.
		//Setting the dialog to be centered.
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(new Dimension(400,150));
		frame.setVisible(true);
	}
	
	/**
	 * This method will generate the game, based on the board choice/color scheme
	 * @param b
	 * @param stones
	 */
	public static void generateGame(Board b, int stones)
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JPanel gamePanel = new JPanel();
		gamePanel.add(b);
		
		JPanel buttonPanel = new JPanel();
		JButton style = new JButton("Quit"); // Added for client usage.
		style.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.dispose();
					}
			
				});
		buttonPanel.add(style);
		
		
		frame.add(gamePanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(500, 400);
		frame.setVisible(true);
	}
	
	/**
	 * This method is mainly responsible for the colors of the board.
	 * @param s
	 */
	public void pickColorScheme(String s)
	{
		// The first array is all of the colors.
		Color[] colors = new Color[] { Color.WHITE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.MAGENTA
				, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.YELLOW};
		
		// This array is all the strings identical to the colors above, which will be used to access later
		String[] color = new String[] { "White", "Cyan", "Dark_Gray", "Gray", "Green", "Magenta"
				, "Orange", "Pink", "Red", "Blue", "Yellow"};
		
		
		JFrame frame = new JFrame(); // Frame that will hold all of the components.
		JTextArea header = new JTextArea("Choose board color: ");
		JTextArea header2 = new JTextArea("Choose pit color: ");
		JComboBox boardColor = new JComboBox();
		
		JComboBox pitColor = new JComboBox();
		for (int i = 0; i < colors.length; i++)
		{
			boardColor.addItem(color[i]); // Adds the string representations of each color.
			pitColor.addItem(color[i]);
		}
		
		
		JPanel boardColorP = new JPanel();
		boardColorP.add(header, BorderLayout.NORTH);
		boardColorP.add(boardColor, BorderLayout.CENTER);
		
		
		JPanel pitColorP = new JPanel(); // Panel that has the combo box and text for pit color.
		pitColorP.add(header2, BorderLayout.NORTH);
		pitColorP.add(pitColor, BorderLayout.CENTER);
		
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String bC = (String) boardColor.getSelectedItem();
						String pC = (String) pitColor.getSelectedItem();
						Color num = null;
						Color num2 = null;
						
						// Each for loop trys to find the color's spot in the array based on the String location in it's array.
						for (int z = 0; z < color.length; z++)
						{
							if (bC.equals(color[z]))
							{
								num = colors[z];
								break;
							}
							
						}
						
						for (int z = 0; z < color.length; z++)
						{
							
							if (pC.equals(color[z]))
							{
								num2 = colors[z];
								break;
							}
						}
						
						
						if (s.equals("Simple"))
						{
							frame.dispose();
							generateGame(new Board(new SimpleBoard(num, num2)), 3); // Generates the board, Created a new constructor for this purpose
						}
						else if (s.equals("Other"))
						{
							frame.dispose();
							generateGame(new Board(new RigidBoard(num, num2)), 3);
						}
						
					}
				});
		
		frame.add(boardColorP, BorderLayout.NORTH);
		frame.add(pitColorP, BorderLayout.CENTER);
		
		frame.add(start, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(500, 400);
		frame.setVisible(true);
	}
	
}
