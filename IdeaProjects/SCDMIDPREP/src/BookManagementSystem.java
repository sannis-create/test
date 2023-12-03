//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class BookManagementSystem {
//
//    private JFrame frame;
//    private JTextField bookNameField;
//    private JTextArea displayArea;
//    private ArrayList<String> books = new ArrayList<>();
//
//    public BookManagementSystem() {
//        frame = new JFrame("Book Management System");
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new FlowLayout());
//
//        JLabel bookNameLabel = new JLabel("Book Name:");
//        frame.add(bookNameLabel);
//
//        bookNameField = new JTextField(20);
//        frame.add(bookNameField);
//
//        JButton addButton = new JButton("Add Book");
//        frame.add(addButton);
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String bookName = bookNameField.getText().trim();
//                if (!bookName.isEmpty()) {
//                    books.add(bookName);
//                    displayBooks();
//                    bookNameField.setText("");
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Enter a valid book name!");
//                }
//            }
//        });
//
//        JButton deleteButton = new JButton("Delete Book");
//        frame.add(deleteButton);
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String bookName = bookNameField.getText().trim();
//                if (books.remove(bookName)) {
//                    displayBooks();
//                    bookNameField.setText("");
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Book not found!");
//                }
//            }
//        });
//
//        displayArea = new JTextArea(10, 30);
//        displayArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(displayArea);
//        frame.add(scrollPane);
//
//        frame.setVisible(true);
//    }
//
//    private void displayBooks() {
//        StringBuilder sb = new StringBuilder();
//        for (String book : books) {
//            sb.append(book).append("\n");
//        }
//        displayArea.setText(sb.toString());
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new BookManagementSystem());
//    }
//}
