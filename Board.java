package ME3;

public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char[][] board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];

	/*
	 * The board object must contain the board state in some manner. You must decide
	 * how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose how
	 * the board is being implemented to other classes. Specifically, the Player
	 * classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve what
	 * the assignment is asking, you'll have to
	 * 
	 */

	public Board() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			System.out.print("|");
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println();
		}
	}

	public boolean containsWin() {
		// checks for horizontal wins
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS - 3; j++) {
				if (board[i][j] != '_' && board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2]
						&& board[i][j + 2] == board[i][j + 3])
					return true;
			}
		}

		// checks for vertical wins
		for (int i = 0; i < NUM_OF_ROW - 3; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (board[i][j] != '_' && board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j]
						&& board[i + 2][j] == board[i + 3][j])
					return true;
			}
		}

		// checks for diagonal wins
		for (int i = 0; i < NUM_OF_ROW - 3; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS - 3; j++) {
				if (board[i][j] != '_' && board[i][j] == board[i + 1][j + 1]
						&& board[i + 1][j + 1] == board[i + 2][j + 2] && board[i + 2][j + 2] == board[i + 3][j + 3])
					return true;
			}
		}

		for (int i = 0; i < NUM_OF_ROW - 3; i++) {
			for (int j = NUM_OF_COLUMNS - 1; j > 2; j--) {
				if (board[i][j] != '_' && board[i][j] == board[i + 1][j - 1]
						&& board[i + 1][j - 1] == board[i + 2][j - 2] && board[i + 2][j - 2] == board[i + 3][j - 3])
					return true;
			}
		}
		return false;
	}

	public boolean isTie() {
		for (int j = 0; j < NUM_OF_COLUMNS; j++) {
			if (board[0][j] == '_') {
				return false;
			}
		}
		return true;
	}

	public void reset() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				board[i][j] = '_';
			}
		}
	}

	/**
	 * @param symbol   - Takes the symbol to input
	 * @param colInput - Takes the column number to input the symbol into
	 */
	public boolean updateBoard(char symbol, int colInput) {
		// starts from the bottom of the column to input, and travels up until there is
		// an open spot to input the symbol
		int i = 5;
		while (i > -1) {
			if (board[i][colInput - 1] == '_') {
				board[i][colInput - 1] = symbol;
				return true;
			}
			i--;
		}
		return false;
	}

	public boolean opponentCanWin(char symbol, int input) {
		char[][] temp = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		boolean canAdd = false;
		int i = 5;
		while (i > -1) {
			if (temp[i][input - 1] == '_') {
				temp[i][input - 1] = 'X';
				canAdd = true;
				break;
			}
			i--;
		}
		if (canAdd) {
			int j = input - 1;

			for (int k = 0; k < NUM_OF_COLUMNS - 3; k++) {
				if ((temp[i][k] != '_' && temp[i][k] != symbol) && (temp[i][k + 1] != '_' && temp[i][k + 1] != symbol)
						&& (temp[i][k + 2] != '_' && temp[i][k + 2] != symbol)
						&& (temp[i][k + 3] != '_' && temp[i][k + 3] != symbol)) {
					return true;
				}
			}
			for (int k = 0; k < NUM_OF_ROW - 3; k++) {
				if ((temp[k][j] != '_' && temp[k][j] != symbol) && (temp[k + 1][j] != '_' && temp[k + 1][j] != symbol)
						&& (temp[k + 2][j] != '_' && temp[k + 2][j] != symbol)
						&& (temp[k + 3][j] != '_' && temp[k + 3][j] != symbol)) {
					return true;
				}
			}
			int minRow = i - Math.min(i, j);
			int minCol = j - Math.min(i, j);

			while (minRow < NUM_OF_ROW - 3 && minCol < NUM_OF_COLUMNS - 3) {
				if ((temp[minRow][minCol] != '_' && temp[minRow][minCol] != symbol)
						&& (temp[minRow + 1][minCol + 1] != '_' && temp[minRow + 1][minCol + 1] != symbol)
						&& (temp[minRow + 2][minCol + 2] != '_' && temp[minRow + 2][minCol + 2] != symbol)
						&& (temp[minRow + 3][minCol + 3] != '_' && temp[minRow + 3][minCol + 3] != symbol)) {
					System.out.println("left diagonal block");
					return true;
				}
				minRow++;
				minCol++;
			}

			int maxRow = i;
			int maxCol = j;

			while (maxRow != 0 && maxCol != 6) {
				maxRow--;
				maxCol++;
			}

			while (maxRow < NUM_OF_ROW - 3 && maxCol > 2) {
				if ((temp[maxRow][maxCol] != '_' && temp[maxRow][maxCol] != symbol)
						&& (temp[maxRow + 1][maxCol - 1] != '_' && temp[maxRow + 1][maxCol - 1] != symbol)
						&& (temp[maxRow + 2][maxCol - 2] != '_' && temp[maxRow + 2][maxCol - 2] != symbol)
						&& (temp[maxRow + 3][maxCol - 3] != '_' && temp[maxRow + 3][maxCol - 3] != symbol)) {
					System.out.println("right diagonal block");
					return true;
				}
				maxRow++;
				maxCol--;
			}
			return false;
		} else {
			return false;
		}

	}

	public boolean playerCanWin(char symbol, int input) {
		char[][] temp = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		boolean canAdd = false;
		int i = 5;
		while (i > -1) {
			if (temp[i][input - 1] == '_') {
				temp[i][input - 1] = symbol;
				canAdd = true;
				break;
			}
			i--;
		}
		if (canAdd) {
			int j = input - 1;
			for (int k = 0; k < NUM_OF_COLUMNS - 3; k++) {
				if (temp[i][k] == symbol && temp[i][k] == temp[i][k + 1] && temp[i][k + 1] == temp[i][k + 2]
						&& temp[i][k + 2] == temp[i][k + 3])
					return true;
			}
			for (int k = 0; k < NUM_OF_ROW - 3; k++) {
				if (temp[k][j] == symbol && temp[k][j] == temp[k + 1][j] && temp[k + 1][j] == temp[k + 2][j]
						&& temp[k + 2][j] == temp[k + 3][j]) {
					return true;
				}
			}
			int minRow = i - Math.min(i, j);
			int minCol = j - Math.min(i, j);
			while (minRow < NUM_OF_ROW - 3 && minCol < NUM_OF_COLUMNS - 3) {
				if (temp[minRow][minCol] == symbol && temp[minRow][minCol] == temp[minRow + 1][minCol + 1]
						&& temp[minRow + 1][minCol + 1] == temp[minRow + 2][minCol + 2]
						&& temp[minRow + 2][minCol + 2] == temp[minRow + 3][minCol + 3]) {
					return true;
				}
				minRow++;
				minCol++;
			}

			int maxRow = i;
			int maxCol = j;
			while (maxRow != 0 && maxCol != 6) {
				maxRow--;
				maxCol++;
			}
			while (maxRow < NUM_OF_ROW - 3 && maxCol > 2) {
				if (temp[maxRow][maxCol] == symbol && temp[maxRow][maxCol] == temp[maxRow + 1][maxCol - 1]
						&& temp[maxRow + 1][maxCol - 1] == temp[maxRow + 2][maxCol - 2]
						&& temp[maxRow + 2][maxCol - 2] == temp[maxRow + 3][maxCol - 3]) {
					return true;
				}
				maxRow++;
				maxCol--;
			}
			return false;
		} else {
			return false;
		}

	}
}