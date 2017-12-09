import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Mancala {

	private Players p1;
	private Players p2;
	private int p1Undos;
	private int p2Undos;
	private int[] lastState1;
	private int[] lastState2; //these two are for copying the last state so we can use it to undo
	private boolean turn; //true == player1's turn; false == player2's turn
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	boolean prevTurn;
	int start; // Could also turn this into a boolean since it's only 0 or 1

	public Mancala(String name1, String name2, int stones){ //determine if we want to randomize the turn, select a turn
		p1 = new Players(name1, stones);
		p2 = new Players(name2, stones);
		p1Undos = 3;
		p2Undos = 3;
		turn = true; //true = player1 turn
		prevTurn = false; 
		start = 0;
	}

	public void attach(ChangeListener l){
		listeners.add(l);
	}

	public void notifyListeners(){
		for(ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
		
		// Print statements to test board state. Remove before submitting
		for (int i = 6; i >= 0; i--)
			System.out.print(p2.getBoard()[i] + " ");
		System.out.println();
		for (int i : p1.getBoard())
			System.out.print(i + " ");
		System.out.println();
	}

	public boolean getTurn(){
		return this.turn;
	}

	public int[] checkPit1(){
		return p1.getBoard();
	}

	public int[] checkPit2(){
		return p2.getBoard();
	}
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

	public void pitLogic(int pit){ //need the index or location
		lastState1 = p1.getBoard().clone();
		lastState2 = p2.getBoard().clone();
		start = 1;
		int index = pit;
		prevTurn = turn;
		if(turn){
			if(lastState1[index] == 0){
				return;
			}
			turn = p1.moveStone(p2, index, turn); //lastState is already set by this point for the undo
		}
		else{
			if(lastState2[index] == 0){
				return;
			}
			turn = p2.moveStone(p1, index, turn); //moves everything and sets turn
		}
		if (checkEnd() && prevTurn == turn){ //Because moveStone changes the turn based on the set rules, this checks if the game ends and flips the turn so that the other player can go through final step
			turn = !turn;
		}
		notifyListeners();
	}

	public void undoBoard(){
		// I think it's a little more clear to do if (start == 0) return then do everything else after 
		if(start != 0){//this ensures undo is unavailable until at least one move has been made
			/* You could remove some repetitive code by doing 
			 * if (turn)
			 * 		stuff
			 * 		if (turn != prevTurn)
			 * 			turn = prevTurn
			 */
			if(turn && turn != prevTurn){ //The turn the undo is called on takes away the other turn's undo amount.
				//take away player 2's undo
				if(p2Undos == 0){
					return; //nothing gets done if they are out of undos
				}
				p2Undos--;
				turn = prevTurn;
			}
			else if(!turn && turn != prevTurn){

				if(p1Undos == 0){
					return;
				}
				p1Undos--;
				turn = prevTurn;
			}
			else if(turn && turn == prevTurn){ 
				if(p1Undos == 0){
					return;
				}
				p1Undos--;
			}

			else{
				if(!turn && turn != prevTurn){ 

					if(p2Undos == 0){
						return;
					}
					p2Undos--;                
				}

			}

			p1.setBoard(lastState1);
			p2.setBoard(lastState2);
			notifyListeners();
		}
	}

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

	public void finalMove(){//call this once checkEnd has run to see if its over or not. THERE IS NO UNDO FROM THIS POINT
		if (turn){//player 1's turn
			lastState1 = p1.getBoard();
			int sum = 0;
			for(int i = 0; i < 6; i++){
				sum += lastState1[i];
				lastState1[i] = 0; // Need to move the stones in pits to the mancala
			}
			lastState1[6] += sum; // += to add the sum to the mancala instead of setting it to the sum
			p1.setBoard(lastState1);
		}
		else{
			lastState2 = p2.getBoard();
			int sum = 0;
			for(int i = 0; i < 6; i++){
				sum += lastState2[i];
				lastState2[i] = 0;
			}
			lastState2[6] += sum;
			p2.setBoard(lastState2);
		}
	}



}