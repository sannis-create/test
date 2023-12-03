import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class BookManagementSys {

    private JFrame frame;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextArea displayArea;
    private ArrayList<Book> books = new ArrayList<>();


    public BookManagementSys()
    {
        loadBooksFromFile();
        frame = new JFrame("Book Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel bookNameLabel = new JLabel("Book Title:\n\n");
        frame.add(bookNameLabel);

        bookNameField = new JTextField(40);
        frame.add(bookNameField);

        JLabel authorNameLabel = new JLabel("Author Name:\n\n");
        frame.add(authorNameLabel);

        authorNameField = new JTextField(35);
        frame.add(authorNameField);

        JButton addButton = new JButton("Add Book");
        frame.add(addButton);
        addButton.addActionListener(e -> {
            String bookName = bookNameField.getText().trim();
            String authorName = authorNameField.getText().trim();
            if (!bookName.isEmpty() && !authorName.isEmpty()) {
                boolean exists = books.stream().anyMatch(b -> b.getTitle().equalsIgnoreCase(bookName) && b.getAuthor().equalsIgnoreCase(authorName));

                if (exists) {
                    JOptionPane.showMessageDialog(frame, "Book already present!");
                } else {
                    books.add(new Book(bookName, authorName));
                    saveBooksToFile();
                    bookNameField.setText("");
                    authorNameField.setText("");
                    displayArea.setText("");
                    JOptionPane.showMessageDialog(frame, "Book added successfully");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Enter valid book and author names!");
            }
        });



        JButton deleteButton = new JButton("Delete");
        frame.add(deleteButton);
        deleteButton.addActionListener(e -> {
            String bookNameToDelete = bookNameField.getText().trim();
            String authorNameToDelete = authorNameField.getText().trim();

            if (!bookNameToDelete.isEmpty() && !authorNameToDelete.isEmpty()) {
                Book bookToDelete = null;
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(bookNameToDelete) && book.getAuthor().equalsIgnoreCase(authorNameToDelete)) {
                        bookToDelete = book;
                        break;
                    }
                }

                if (bookToDelete != null) {
                    books.remove(bookToDelete);
                    saveBooksToFile();
                    displayArea.setText("");  // Clear the display area
                    bookNameField.setText("");
                    authorNameField.setText("");
                    JOptionPane.showMessageDialog(frame, "Book deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Book not found!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter both book title and author name to delete!");
            }
        });


        // Create the Clear button
        JButton clearButton = new JButton("Clear");
        frame.add(clearButton);

        clearButton.addActionListener(e -> {
            bookNameField.setText("");      // Clear the book name input field
            authorNameField.setText("");    // Clear the author name input field
            displayArea.setText("");        // Clear the display pane
        });

        JButton importButton = new JButton("Import from CSV");
        frame.add(importButton);
        importButton.addActionListener(e -> importFromCSV());



        JButton exportButton = new JButton("Export to CSV");
        frame.add(exportButton);
        exportButton.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.csv"))) {
                for (Book book : books) {
                    writer.write(book.toCSV());
                    writer.newLine();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        JButton searchButton = new JButton("Search Book");
        frame.add(searchButton);
        searchButton.addActionListener(e -> {
            String bookName = bookNameField.getText().trim();
            displayBook(bookName);
        });

        displayArea = new JTextArea(16, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    private void importFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Book newBook = new Book(parts[0].trim(), parts[1].trim());

                    // Check if the book is not already present in the list
                    boolean exists = books.stream().anyMatch(b -> b.getTitle().equalsIgnoreCase(newBook.getTitle()) && b.getAuthor().equalsIgnoreCase(newBook.getAuthor()));

                    if (!exists) {
                        books.add(newBook);
                    }
                }
            }
            saveBooksToFile(); // Save to the main book.txt file after importing
            JOptionPane.showMessageDialog(frame, "Books imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error importing books from CSV!");
        }
    }

    private void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("book.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    books.add(new Book(parts[0].trim(), parts[1].trim()));  // trim inputs
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("book.txt"))) {
            for (Book book : books)
            {
                bw.write(book.title + ";" + book.author + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void displayBooks() {
//        StringBuilder sb = new StringBuilder();
//        for (Book book : books) {
//            sb.append("Title: ").append(book.title).append(", Author: ").append(book.author).append("\n");
//        }
//        displayArea.setText(sb.toString());
//    }

    private void displayBook(String title) {
        for (Book book : books)
        {
            if (book.title.equalsIgnoreCase(title)) {
                displayArea.setText("Title: " + book.title + "\nAuthor: " + book.author);
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Book not found!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookManagementSys());
    }

    private static class Book
    {
        String title;
        String author;


        Book(String title, String author)
        {
            this.title = title;
            this.author = author;
        }


        public String getTitle() {
            return title;
        }

        public String getAuthor()
        {
            return author;
        }

        public String toCSV()
        {
            return title + "," + author ;
        }


        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + title.toLowerCase().hashCode();
            result = 31 * result + author.toLowerCase().hashCode();
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return title.equalsIgnoreCase(book.title) && author.equalsIgnoreCase(book.author);
        }

    }
}
