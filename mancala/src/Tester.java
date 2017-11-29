import javax.swing.*;
import java.awt.*;


public class Tester 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		

		Board b = new Board(new RigidBoard());
		JPanel filler = new JPanel();
		filler.add(b);
		frame.add(filler);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
