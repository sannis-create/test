import javax.swing.*;
import java.awt.*;   // flow layout

public class Q2
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Flow Button");
        frame.setSize(500, 100);
        ImageIcon icon = new ImageIcon("National_University_of_Computer_and_Emerging_Sciences_logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPane = new JPanel(new FlowLayout());
        JButton button1 = new JButton("NiHao Button# 1");
        JButton button2 = new JButton("Aneyoung Button# 2");
        JButton button3 = new JButton("Hello Button# 3");

        buttonPane.add(button1);
        buttonPane.add(button2);
        buttonPane.add(button3);
        frame.add(buttonPane);
        frame.setVisible(true);
    }
}
