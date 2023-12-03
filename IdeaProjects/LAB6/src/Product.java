import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Product {
    private String pname;
    private double price;
    private int quant;

    public Product(String pname, double price, int quant) {
        this.pname = pname;
        this.price = price;
        this.quant = quant;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }

    public int getQuant() {
        return quant;
    }
}

class ProductTableModel extends DefaultTableModel {
    public ProductTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
}

 class Amazon
 {
    private JFrame frame;
    private JTextField text;
    private JTextField pricer;
    private JTextField quanter;
    private JTable table;
    private ProductTableModel tableModel;
    private JLabel label;

    public static void main(String[] args) {
            new Amazon().tableFormat();
        }

    private void tableFormat() {
        frame = new JFrame("Amazon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create table model and table
        Object[] columnNames = {"Name", "Price", "Quantity"};
        tableModel = new ProductTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create input fields and labels
        JPanel inputPanel = new JPanel(new FlowLayout());
        text = new JTextField(10);
        pricer = new JTextField(10);
        quanter = new JTextField(10);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(text);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(pricer);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quanter);

        // Add to Cart button
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(e);
            }
        });
        inputPanel.add(addButton);

        JButton addButton1 = new JButton("Remove");
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removebuttonn(e);
            }
        });
        inputPanel.add(addButton1);

        label = new JLabel("Total: 0.0");
        inputPanel.add(label);

        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 300);
        frame.setVisible(true);
    }

    private void addToCart(ActionEvent e)
    {
        String name = text.getText();
        double price = Double.parseDouble(pricer.getText());
        int quantity = Integer.parseInt(quanter.getText());
                for (int i = 0; i < tableModel.getRowCount(); i++)
                {
                    if (name.equals(tableModel.getValueAt(i, 0)))
                    {
                        int currentQuantity = (int) tableModel.getValueAt(i, 2);
                        tableModel.setValueAt(currentQuantity + quantity, i, 2);
                        updateTotal();
                        return;
                    }
                }

                double totalPrice = price * quantity;
                tableModel.addRow(new Object[]{name, price, quantity});
                updateTotal();
            }

         public void removebuttonn(ActionEvent e)
         {
             int selectedRow = table.getSelectedRow();
             if (selectedRow >= 0) {
                 tableModel.removeRow(selectedRow);
                 updateTotal();
             }
         }

    private void addProductToTable(Product product)
    {
        Object[] rowData = {product.getPname(), product.getPrice(), product.getQuant() };
        tableModel.addRow(rowData);
    }

    private void updateTotal() {
        double total = 0.0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            double price = (double) tableModel.getValueAt(row, 1);
            int quantity = (int) tableModel.getValueAt(row, 2);
            total += price * quantity;
        }
        label.setText("Total: " + total);
    }

    private void clearInputFields() {
        text.setText("");
        pricer.setText("");
        quanter.setText("");
    }
}
