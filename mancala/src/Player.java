
/**
 * This class is responsible for the game logic of the program.
 * It will contain information on the player, as well as the amount of stones that player has.
 * @author Andy, Hovsep Lalikian, Aldrich
 *
 */
public class Player {
	private String name;
	private int[] board; //6 is mancala, 0-5 are the pits from left to right from player's perspective

	public Player(String player, int stones){
		name = player;
		board = new int[7];
		for(int i = 0; i < board.length - 1; i++){
			board[i] = stones;
		}
		board[6] = 0;

	}

	/**
	 * This method will return the name of the player
	 * @return the name of the player
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * This method will get the board of the player 
	 * @return the player's side of the board.
	 */
	public int[] getBoard(){
		return board;
	}

	/**
	 * This method will set the player's board based on the clicked pit.
	 * @param playerBoard the new board
	 */
	public void setBoard(int[] playerBoard){
		this.board = playerBoard;
	}

	public boolean moveStone(Player other, int index, boolean turn){
		int playerStone = this.board[index]; //hold all stones in picked pit in hand
		this.board[index] = 0; //since all stones are picked up the picked pit is now empty
		int set = index + 1; //first stone dropped from hand goes into the next pit
		int[] otherBoard = other.getBoard(); //may need the other player's board
		while(playerStone > 0){ //while stones are left in hand
			for(int i = set; i < 7 && playerStone != 0; i++){ //either out of pits on current player side or run out of stones
				this.board[i] += 1; //add a stone to this pit
				playerStone--; //take one stone out of hand
				if(playerStone == 0 && i == 6){ //if current player places last stone in hand into their own mancala, the turn stays the same
					return turn; //keeps it the current player's turn
				}
				if(playerStone == 0 && this.board[i] == 1){ //if current player has no stones left in hand and the board the player just dropped a stone into was empty
					//this.board[i] == 1 because this is checking after the stone has been placed so 1 would mean it was empty before
					int sum = this.board[i] + otherBoard[5-i]; //this would add the stones in the current pit (1) with the number of stones in the opposing side pit
					this.board[6] += sum; //add the total number to player's mancala
					this.board[i] = 0;
					otherBoard[5-i] = 0; //reset the two pits to be empty cause we took the stones
				}
			}
			set = 0;
			if(playerStone > 0){ //if stones are still in hand but we are out of pits
				for(int j = 0; j < 6 && playerStone != 0; j++){
					otherBoard[j] += 1;
					playerStone--;
					other.setBoard(otherBoard);
				}
			}
		}
		other.setBoard(otherBoard);
		return !turn; //if the last stone lands anywhere but the player's own mancala, turn changes


	} //this should move the stones so that the board gets updated

}
