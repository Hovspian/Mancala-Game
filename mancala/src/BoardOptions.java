import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class BoardOptions 
{
	public BoardOptions()
	{
		JFrame frame = new JFrame();


		JTextArea header = new JTextArea("Choose a board layout:");
		header.setEditable(false);
		frame.add(header, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		JButton simpleButton = new JButton("Simple");
		JButton otherButton = new JButton("Other");
		simpleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new Board(new Mancala("a", "b", 3), new SimpleBoard());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		otherButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new Board(new Mancala("a", "b", 3), new RigidBoard());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonPanel.add(simpleButton);
		buttonPanel.add(otherButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);


		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
