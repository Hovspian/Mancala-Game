public class Players {
	private String name;
	private int[] board; //6 is mancala, 0-5 are the pits from left to right from player's perspective

	public Players(String player, int stones){
		name = player;
		board = new int[7];
		for(int i = 0; i < board.length; i++){
			board[i] = stones;
		}

	}

	public String getName(){
		return this.name;
	}

	public int[] getBoard(){
		return this.board;
	}

	public void setBoard(int[] playerBoard){
		this.board = playerBoard;
	}

	public boolean moveStone(Players other, int index, boolean turn){
		int playerStone = this.board[index];
		this.board[index] = 0;
		int set = index + 1;
		int[] otherBoard = other.getBoard();
		while(playerStone > 0){
			for(int i = set; i < 7 && playerStone != 0; i++){ //either out of pits on current player side or run out of stones
				this.board[i] += 1;
				playerStone--;
				if(playerStone == 0 && i == 6){ //if current player places last stone in hand into their own mancala, the turn stays the same
					return turn;
				}
				if(playerStone == 0 && this.board[i] == 1){
					int sum = this.board[i] + otherBoard[5-i];
					this.board[6] += sum;
				}
			}
			set = 0;
			if(playerStone > 0){
				for(int j = 0; j < 6 && playerStone != 0; j++){
					otherBoard[j] += 1;
					playerStone--;
				}
			}
		}
		other.setBoard(otherBoard);
		return !turn; //if the last stone lands anywhere but the player's own mancala, turn changes


	} //this should move the stones so that the board gets updated

}