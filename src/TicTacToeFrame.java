
import javax.swing.*;
        import java.awt.*;

public class TicTacToeFrame extends JFrame {

    private TicTacToeButton[][] board = new TicTacToeButton[3][3];
    private boolean xTurn = true;
    private int moveCount = 0;

    public TicTacToeFrame() {
        super("Tic Tac Toe GUI");
        buildGUI();
    }

    private void buildGUI() {
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                TicTacToeButton btn = new TicTacToeButton();
                board[r][c] = btn;

                int row = r;
                int col = c;

                btn.addActionListener(e -> handleMove(row, col));

                boardPanel.add(btn);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
    }

    private void handleMove(int r, int c) {
        TicTacToeButton btn = board[r][c];

        if (!btn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Illegal move. Choose another square.");
            return;
        }

        btn.setValue(xTurn ? "X" : "O");
        moveCount++;

        if (moveCount >= 5 && checkWin()) {
            JOptionPane.showMessageDialog(this, (xTurn ? "X" : "O") + " wins!");
            askPlayAgain();
            return;
        }

        if (moveCount >= 7 && checkTie()) {
            JOptionPane.showMessageDialog(this, "Tie game!");
            askPlayAgain();
            return;
        }

        xTurn = !xTurn;
    }

    private boolean checkWin() {
        String p = xTurn ? "X" : "O";

        // rows
        for (int r = 0; r < 3; r++)
            if (board[r][0].getValue().equals(p) &&
                    board[r][1].getValue().equals(p) &&
                    board[r][2].getValue().equals(p))
                return true;

        // cols
        for (int c = 0; c < 3; c++)
            if (board[0][c].getValue().equals(p) &&
                    board[1][c].getValue().equals(p) &&
                    board[2][c].getValue().equals(p))
                return true;

        // diagonals
        if (board[0][0].getValue().equals(p) &&
                board[1][1].getValue().equals(p) &&
                board[2][2].getValue().equals(p))
            return true;

        if (board[0][2].getValue().equals(p) &&
                board[1][1].getValue().equals(p) &&
                board[2][0].getValue().equals(p))
            return true;

        return false;
    }

    private boolean checkTie() {
        // full board tie
        if (moveCount == 9) return true;

        // not-full-board tie (no possible wins left)
        // Simple version: check if any empty square exists
        // You can expand this if needed
        return false;
    }

    private void askPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    private void resetBoard() {
        moveCount = 0;
        xTurn = true;

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c].setValue("");
    }
}
