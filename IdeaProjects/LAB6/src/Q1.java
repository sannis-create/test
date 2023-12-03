import javax.swing.*;
public class Q1
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Inventory Table");
        frame.setSize(500, 200);
        ImageIcon icon = new ImageIcon("National_University_of_Computer_and_Emerging_Sciences_logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] cols = {"Product", "Quantity", "Price"};
        String[][] rows = {
                {"Product A", "12", "1300"},
                {"Product B", "10", "2444"},
                {"Product C", "22.9", "2890"},
                {"Product D", "0.15", "233"},
                {"Product E", "1.45", "331"},
                {"Product F", "3.5", "3531"},
                {"Product G", "15", "3310"},
                {"Product H", "14.5", "3131"}};

        JTable j = new JTable(rows, cols);
        j.setBounds(40, 40, 200, 300);
        JScrollPane pane = new JScrollPane(j);
        frame.add(pane);
        frame.setVisible(true);
    }
}