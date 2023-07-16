import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons;
    private boolean isPlayerX;
    private boolean gameEnded;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        isPlayerX = true;
        gameEnded = false;

        // Create buttons and add ActionListener
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int buttonIndex = -1;

        // Find the index of the clicked button
        for (int i = 0; i < 9; i++) {
            if (buttons[i] == button) {
                buttonIndex = i;
                break;
            }
        }

        // Ignore button clicks if the game has ended or the button is already filled
        if (gameEnded || !button.getText().isEmpty())
            return;

        // Set the button text based on the current player
        if (isPlayerX)
            button.setText("X");
        else
            button.setText("O");

        // Check if the current player has won
        if (checkWin()) {
            String winner = isPlayerX ? "X" : "O";
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            gameEnded = true;
            return;
        }

        // Check if it's a tie
        if (checkTie()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            gameEnded = true;
            return;
        }

        // Switch the player
        isPlayerX = !isPlayerX;
    }

    private boolean checkWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!board[i].isEmpty() && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!board[i].isEmpty() && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                return true;
            }
        }

        // Check diagonals
        if (!board[0].isEmpty() && board[0].equals(board[4]) && board[0].equals(board[8])) {
            return true;
        }
        if (!board[2].isEmpty() && board[2].equals(board[4]) && board[2].equals(board[6])) {
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}
