
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class is responsible for creating the formatting options of the game. 
 * You will be able to choose the format and color scheme of the game and apply it to the game board.
 * @author Andy, Hovsep, Aldrich
 *
 */
public class BoardOptions 
{
	private Mancala m;
	
	/**
	 * This constructor will produce the initial frame that will allow you to choose the frame
	 * and colors of the board.
	 */
	public BoardOptions()
	{
		// Colors for the board and pit.
		Color[] colors = new Color[] { Color.WHITE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.MAGENTA
				, Color.ORANGE, Color.PINK, Color.RED, Color.BLUE, Color.YELLOW};

		// This array is all the strings identical to the colors above, which will be used to access later
		String[] color = new String[] { "White", "Cyan", "Dark_Gray", "Gray", "Green", "Magenta"
				, "Orange", "Pink", "Red", "Blue", "Yellow"};

		// First frame, asking for the board layout and amount of stones
		JFrame frame = new JFrame();
		frame.setTitle("Mancala v1 (CS151)");
		frame.setBackground(Color.WHITE);
		JTextArea header = new JTextArea("Choose a board layout:");
		header.setEditable(false);

		// This panel will create the header and box for layout.
		JPanel boardPanel = new JPanel();
		boardPanel.setOpaque(false);
		boardPanel.setBackground(Color.WHITE);
		boardPanel.setLayout(new BorderLayout());
		boardPanel.add(header, BorderLayout.NORTH);
		JComboBox<String> box = new JComboBox<String>();
		box.setBackground(Color.WHITE);
		box.addItem("Simple");
		box.addItem("Rigid");
		boardPanel.add(box, BorderLayout.CENTER);

		// Combo boxes for the colors
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BorderLayout());

		JComboBox<String> boardColor = new JComboBox<String>();	
		boardColor.setBackground(Color.WHITE);
		boardColor.addItem("Select Board Color");
		JComboBox<String> pitColor = new JComboBox<String>();
		pitColor.setBackground(Color.WHITE);
		pitColor.addItem("Select Pit Color");
		for (int i = 0; i < colors.length; i++)
		{
			boardColor.addItem(color[i]); // Adds the string representations of each color.
			pitColor.addItem(color[i]);
		}
		colorPanel.add(boardColor, BorderLayout.CENTER);
		colorPanel.add(pitColor, BorderLayout.SOUTH);

		JPanel pitPanel = new JPanel();
		pitPanel.setBackground(Color.WHITE);
		JButton accept = new JButton("Accept");

		JTextArea header2 = new JTextArea("Choose the max amount of stones: ");
		header2.setOpaque(false);
		header2.setEditable(false);
		JComboBox<String> pitNum = new JComboBox<String>();
		pitNum.setBackground(Color.WHITE);
		pitNum.addItem("3");
		pitNum.addItem("4");
		pitPanel.add(header2, BorderLayout.NORTH);
		pitPanel.add(pitNum, BorderLayout.CENTER);

		// Combo box to choose the amount of pit stones for the game.


		frame.add(boardPanel, BorderLayout.NORTH);
		frame.add(pitPanel, BorderLayout.CENTER);
		frame.add(colorPanel, BorderLayout.WEST);

		accept.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent arg0) 
			{
				int stones = Integer.parseInt((String)pitNum.getSelectedItem()); // Gets the item chosen from the Combo Box
				m = new Mancala("A", "B", stones);

				// Reading all of the choices in each box and putting it into the board
				String format = (String) box.getSelectedItem();
				String bC = (String) boardColor.getSelectedItem();
				String pC = (String) pitColor.getSelectedItem();


				Color num = null;
				Color num2 = null;

				// Each for loop tries to find the color's spot in the array based on the String location in it's array.
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
				if (bC.equals("Select Board Color"))
				{
					num = Color.WHITE;
				}
				if (pC.equals("Select Pit Color"))
				{
					num2 = Color.WHITE;
				}

				if (format.equals("Simple"))
				{
					frame.dispose();
					pickColorScheme("Simple", num, num2); // Added the two parameters, one for board and the other for pit colors

				}
				else if (format.equals("Rigid"))
				{
					frame.dispose();
					pickColorScheme("Rigid", num, num2);
				}
			}			
		});

		frame.add(accept, BorderLayout.SOUTH); // Accept button that will transitions into different frames.
		
		frame.setLocation(800, 500); // Changes the location of the dialog box to start the game.
		//Setting the dialog to be centered.


		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * This method will generate the game, based on the board choice/color scheme
	 * @param b the type of board that this game will be played on.
	 * @param stones The amount of stones that will be inputed into the board.
	 */
	private static void generateGame(Board b, int stones)
	{
		JFrame frame = new JFrame();
		frame.setTitle("Mancala Project");
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
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				Mancala game = b.getGame();
				game.undoBoard();
			}

		});

		buttonPanel.add(style);
		buttonPanel.add(undo);


		frame.add(gamePanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(500, 400);
		frame.setVisible(true);
	}

	/**
	 * This method is mainly responsible for the colors of the board.
	 * @param s The String that will determine what kind of board will be implemented.
	 * @param bC the color of the board.
	 * @param pC the Color of the pits.
	 */
	private void pickColorScheme(String s, Color bC, Color pC) // Added 2 parameters for the game, left the generateGame method alone so it will just forward the duty to it
	{
		if (s.equals("Simple"))
		{

			Board b = new Board(m, new SimpleBoard(bC, pC));
			generateGame(b, 3); // Generates the board, Created a new constructor for this purpose
			m.attach(b);
		}
		else if (s.equals("Rigid"))
		{

			Board b = new Board(m, new RigidBoard(bC, pC));
			generateGame(b, 3);
			m.attach(b);
		}

	}
	/**
	 * This is the main method that will be used to run the Mancala Game.
	 * @param args the argument for the program to run.
	 */
	public static void main(String[] args)
	{
		new BoardOptions();
	}

}
