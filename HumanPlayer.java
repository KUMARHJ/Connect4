package ME3;

import java.util.Scanner;

public class HumanPlayer extends Player{
	
	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}
	
	public void makeMove(Board b) {
		Scanner scn = new Scanner(System.in);
		int input;
		System.out.println(name + ", please input your move: ");
		input = scn.nextInt();
		b.updateBoard(symbol, input);
	}
}
