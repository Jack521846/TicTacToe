import javax.swing.*;

public class TicTacToeButton extends JButton {

    private String value = ""; // "", "X", or "O"

    public TicTacToeButton() {
        super("");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String v) {
        value = v;
        setText(v);
    }

    public boolean isEmpty() {
        return value.equals("");
    }
}

