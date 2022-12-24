package ME3;

import java.util.Random;

public class AIPlayer extends Player {

	private boolean firstTurn;

	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
		firstTurn = true;
	}

	public void makeMove(Board b) {
		int input;
		Random rand = new Random();
		boolean done = false;

		if (firstTurn) {
			System.out.println("first");
			input = rand.nextInt(7)+1;
			while (!b.updateBoard(symbol, input)) {
				input = rand.nextInt(7)+1;
			}
			firstTurn = false;
		} else {
			for (int i = 1; i < 8; i++) {
				if (b.playerCanWin(symbol, i)) {
					b.updateBoard(super.symbol, i);
					done = true;
					break;
				}
			}	
			if (!done) {
				for (int i = 1; i < 8; i++) {
					if (b.opponentCanWin(symbol, i)) {
						b.updateBoard(super.symbol, i);
						done = true;
						break;
					}
				}
			}
			if (!done) {
				input = rand.nextInt(7)+1;
				while (!b.updateBoard(symbol, input)) {
					input = rand.nextInt(7)+1;
				}
			}

		}
	}
}