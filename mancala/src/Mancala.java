import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This Class is responsible for the modeling of the Mancala game, it will serve as the actual game logic that will fit in with the board.
 * @author Andy, Hovsep, Aldrich 
 *
 */
public class Mancala {

	private Player p1;
	private Player p2;
	private int p1Undos;
	private int p2Undos;
	private int[] lastState1;
	private int[] lastState2; //these two are for copying the last state so we can use it to undo
	private boolean turn; //true == player1's turn; false == player2's turn
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	boolean prevTurn;
	boolean start;

	/**
	 * This constructor will produce the players, as well as the stone number for each pit (either 3 or 4)
	 * @param name1 The name of the first player
	 * @param name2 The name of the second player
	 * @param stones The number of stones to be used in the game.
	 */
	public Mancala(String name1, String name2, int stones){ //determine if we want to randomize the turn, select a turn
		p1 = new Player(name1, stones);
		p2 = new Player(name2, stones);
		p1Undos = 3;
		p2Undos = 3;
		turn = true; //true = player1 turn
		prevTurn = false; 
		start = true;
	}

	/**
	 * This method will attach a changelistener to the program.
	 * @param l the changelistener to be added.
	 */
	public void attach(ChangeListener l){
		listeners.add(l);
	}

	/**
	 * This method will notify the changelisteners when the model changes.
	 * The view will update to the corresponding changes.
	 */
	public void notifyListeners(){
		for(ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public boolean getTurn(){
		return this.turn;
	}

	/**
	 * This method will return the first player's current board.
	 * @return board the first Players board.
	 */
	public int[] checkPit1(){
		return p1.getBoard();
	}

	/**
	 * This method will return the second player's current board.
	 * @return board the second players board
	 */
	public int[] checkPit2(){
		return p2.getBoard();
	}

	/**
	 * This method will return the Winner of the game
	 * @return the name of the winner of the Mancala Game.
	 */
	public String winner(){//called when there's no more undo to do, at the very end to determine winner
		lastState1 = p1.getBoard().clone();
		lastState2 = p2.getBoard().clone();
		if(lastState1[6] > lastState2[6]){
			return p1.getName();
		}
		else if(lastState1[6] < lastState2[6]){
			return p2.getName();
		}
		else{
			return "Tie";
		}
	}

	/**
	 * This method will move the stones in it's respective pits, based on the pit that was clicked on in the view.
	 * 
	 * @param pit the pit that was clicked on the view.
	 */
	public void pitLogic(int pit){ //need the index or location
		lastState1 = p1.getBoard().clone();
		lastState2 = p2.getBoard().clone();
		start = false;
		prevTurn = turn;
		if(turn){
			if(lastState1[pit] == 0){
				return;
			}
			turn = p1.moveStone(p2, pit, turn); //lastState is already set by this point for the undo
		}
		else{
			if(lastState2[pit] == 0){
				return;
			}
			turn = p2.moveStone(p1, pit, turn); //moves everything and sets turn
		}
		if (checkEnd()){ //Because moveStone changes the turn based on the set rules, this checks if the game ends and flips the turn so that the other player can go through final step
			finalMove();

		}
		notifyListeners();
	}

	/**
	 * This method will undo the turn that was previously done.
	 * 3 undos per player.
	 */
	public void undoBoard(){
		if(start) //this ensures undo is unavailable until at least one move has been made
			return;
		if(turn){ //The turn the undo is called on takes away the other turn's undo amount.
			//take away player 2's undo
			if(p2Undos == 0){
				return; //nothing gets done if they are out of undos
			}
			p2Undos--;
			if (turn != prevTurn)
				turn = prevTurn;
		}
		else{
			if(p1Undos == 0){
				return;
			}
			p1Undos--;
			if (turn != prevTurn)
				turn = prevTurn;
		}



		p1.setBoard(lastState1);
		p2.setBoard(lastState2);
		notifyListeners();

	}

	/**
	 * This method will check if one side of the board is completely empty.
	 * In this case the game will be over.
	 * @return true if the Game is over, False if it isn't.
	 */
	public boolean checkEnd(){ //returns false is the game is not over, returns true if one side of the board is cleared
		int[] testState1 = p1.getBoard(); // Doesn't need to be a clone because values won't change during method execution
		int[] testState2 = p2.getBoard();
		int state1 = 0;
		int state2 = 0;

		for(int i = 0; i < 6; i++){
			state1 += testState1[i];
			state2 += testState2[i];
		}

		if(state1 == 0 || state2 == 0){ // Should trigger if either side of the board is empty. Alternatively could do state1 != 0 && state2 != 0
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This method will move the rest of the stones of the player's board into the mancala pits.
	 */
	private void finalMove(){//call this once checkEnd has run to see if its over or not. THERE IS NO UNDO FROM THIS POINT
		lastState1 = p1.getBoard();
		int sum1 = 0;
		for(int i = 0; i < 6; i++){
			sum1 += lastState1[i];
			lastState1[i] = 0; // Need to move the stones in pits to the mancala
		}
		lastState1[6] += sum1; // += to add the sum to the mancala instead of setting it to the sum
		p1.setBoard(lastState1);


		lastState2 = p2.getBoard();
		int sum2 = 0;
		for(int i = 0; i < 6; i++){
			sum2 += lastState2[i];
			lastState2[i] = 0;
		}
		lastState2[6] += sum2;
		p2.setBoard(lastState2);
	}



}
