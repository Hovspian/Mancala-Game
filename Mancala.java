/**
 *
 * @author amp03
 */
public class Mancala {

    private Players p1;
    private Players p2;
    private int[] lastState1;
    private int[] lastState2; //these two are for copying the last state so we can use it to undo
    private boolean turn; //true == player1's turn; false == player2's turn
    
    public Mancala(String name1, String name2, int stones){ //determine if we want to randomize the turn, select a turn
        p1 = new Players(name1, stones);
        p2 = new Players(name2, stones);
        turn = true; //true = player1 turn
    }
    
    public boolean getTurn(){
        return this.turn;
    }
    public String winner(){//called when there's no more undo to do, at the very end to determine winner
        lastState1 = p1.getBoard();
        lastState2 = p2.getBoard();
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
        lastState1 = p1.getBoard();
        lastState2 = p2.getBoard();
        int index = pit;
        boolean prevTurn = turn;
        if(turn){
            turn = p1.moveStone(p2, index, turn);
        }
        else{
            turn = p2.moveStone(p1, index, turn); //moves everything and sets turn
        }
        if (checkEnd() && prevTurn == turn){ //if the game's over, but the player that went dropped the last stone into their own mancala
            turn = !turn;
        }
        
    }
    
    public void undoBoard(){
        p1.setBoard(lastState1);
        p2.setBoard(lastState2);
    }
    
    public boolean checkEnd(){ //returns false is the game is not over, returns true if one side of the board is cleared
        lastState1 = p1.getBoard();
        lastState2 = p2.getBoard();
        
        boolean state = false;
        for(int i = 0; i < 6; i++){
            if(lastState1[i] == 0 || lastState2[i] == 0){
                state = true;
            }
            else{
                state = false;
                i = 6;
            }
        }
        
        return state;
    }
    
    public void finalMove(){//call this once checkEnd has run to see if its over or not
        if (turn){//player 1's turn
            lastState1 = p1.getBoard();
            int sum = 0;
            for(int i = 0; i < 6; i++){
                sum += lastState1[i];
            }
            lastState1[6] = sum;
            p1.setBoard(lastState1);
        }
        if(!turn){
            lastState2 = p2.getBoard();
            int sum = 0;
            for(int i = 0; i < 6; i++){
                sum += lastState2[i];
            }
            lastState2[6] = sum;
            p2.setBoard(lastState2);
        }
    }
    
    
    
}
