import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MyCalculator {

  private final JTextField text;
  private double firstOperand = 0;
  private String op = "";
  private boolean flag = false;

  public MyCalculator()
  {
    JFrame frame = new JFrame("FAST Calculator");
    frame.setSize(300, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icon = new ImageIcon("National_University_of_Computer_and_Emerging_Sciences_logo.png");
    frame.setIconImage(icon.getImage());
    frame.setLayout(new BorderLayout());

    text = new JTextField();
    text.setSize(400, 400);
    text.setEditable(false);
    frame.add(text, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 4));

    String[] buttonLabels = {
            "C", "8", "9", "/",
            "5", "6", "7", "*",
            "2", "3", "4", "+",
            "0", "1", "=", "-"
    };

      for (int i = 0; i < buttonLabels.length; i++)
      {
          String label = buttonLabels[i];
          JButton button = new JButton(label);
          button.addActionListener(new ButtonClickListener());
          buttonPanel.add(button);
      }

    frame.add(buttonPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  private double performOperation(double n1, double n2, String op)
  {
    if (Objects.equals(op, "+")) {
      return n1 + n2;
    } else if (Objects.equals(op, "-")) {
      return n1 - n2;
    } else if (Objects.equals(op, "*")) {
      return n1 * n2;
    } else if (Objects.equals(op, "/")) {
      if (n2 != 0) {
        return n1 / n2;
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Division by zero not possible", "Error Prompt", JOptionPane.ERROR_MESSAGE);
        return 0;
      }
    }
    return 0;
  }

  private class ButtonClickListener implements ActionListener
  {
    private void digitClick(String digit)
    {
      if (flag)
      {
        text.setText(digit);
        flag = false;
      }
      else
      {
        text.setText(text.getText() + digit);
      }
    }

    public void actionPerformed(ActionEvent e)
    {
      JButton source = (JButton) e.getSource();
      String buttonText = source.getText();

      if (buttonText.matches("[0-9]"))
      {
        digitClick(buttonText);
      }
      else if (buttonText.matches("[+\\-*/]"))
      {
        operatorClick(buttonText);
      }
      else if (buttonText.equals("="))
      {
        equalClick();
      }
      else if (buttonText.equals("C"))
      {
        clearClick();
      }
    }

    private void clearClick()
    {
      text.setText(" ");
      firstOperand = 0;
      op = "";
      flag = false;
    }

    private void operatorClick(String clickedOp)
    {
      firstOperand = Double.parseDouble(text.getText());
      op = clickedOp;
      flag = true;
    }

    private void equalClick()
    {
      double secondOperand = Double.parseDouble(text.getText());
      double result = performOperation(firstOperand, secondOperand, op);
      text.setText(String.valueOf(result));
      flag = true;
    }
  }

  public static void main(String[] args)
  {
      SwingUtilities.invokeLater(() -> {
      MyCalculator calculator = new MyCalculator();
    });
  }
}
