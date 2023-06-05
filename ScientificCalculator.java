import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class ScientificCalculator implements ActionListener {

    private JFrame frame;
    private JTextField inputField;
    private JLabel outputLabel;
    private JButton[] buttons;
    private String currentInput;

    ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);

        outputLabel = new JLabel("");
        outputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        outputLabel.setHorizontalAlignment(JLabel.RIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 5, 5, 5));

        buttons = new JButton[]{
                new JButton("7"), new JButton("8"), new JButton("9"), new JButton("/"), new JButton("sqrt"),
                new JButton("4"), new JButton("5"), new JButton("6"), new JButton("*"), new JButton("cbrt"),
                new JButton("1"), new JButton("2"), new JButton("3"), new JButton("-"), new JButton("x^2"),
                new JButton("0"), new JButton("."), new JButton("(-)"), new JButton("+"), new JButton("="),
                new JButton("sin"), new JButton("cos"), new JButton("tan"), new JButton("log"), new JButton("exp"),
                new JButton("asin"), new JButton("acos"), new JButton("atan"), new JButton("log10"), new JButton("x^y"),
                new JButton("ln"), new JButton("floor"), new JButton("ceil"), new JButton("abs"), new JButton("mod")
        };

        for (JButton button : buttons) {
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(button);
        }

        frame.add(inputField, BorderLayout.NORTH);
        frame.add(outputLabel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        currentInput = "";
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "=":
                calculateResult();
                break;
            case "C":
                clearInput();
                break;
            case "(-)":
                negateInput();
                break;
            default:
                currentInput += buttonText;
                inputField.setText(currentInput);
                break;
        }
    }

    private void calculateResult() {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            Object result = engine.eval("Math." + currentInput);
            outputLabel.setText("Result: " + result);
            clearInput();
        } catch (ScriptException e) {
            outputLabel.setText("Error: Invalid Expression");
            clearInput();
        }
    }

    private void clearInput() {
        currentInput = "";
        inputField.setText("");
    }

    private void negateInput() {
        if (!currentInput.isEmpty()) {
            char firstChar = currentInput.charAt(0);
            if (Character.isDigit(firstChar) || firstChar == '.') {
                currentInput = "-" + currentInput;
                inputField.setText(currentInput);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator();
            }
        });
    }
}
